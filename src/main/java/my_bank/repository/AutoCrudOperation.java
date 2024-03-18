package my_bank.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my_bank.dbConnection.DbConnect;
import static my_bank.repository.CaseConverter.convertToSnakeCase;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AutoCrudOperation<T> implements CrudOperation<T> {
    @Getter private final T model;

    @Override
    public T save(T toSave) {
        DbConnect dbConnect = new DbConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Class<?> clazz = toSave.getClass();
        String className = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        List<Field> fieldList = new ArrayList<>();
        T returned = null;

        try {
            connection = dbConnect.createConnection();
            for (Field field : fields) {
                field.setAccessible(true);
                if (!columns.isEmpty()) {
                    columns.append(", ");
                    values.append(", ");
                }
                columns.append(convertToSnakeCase(field.getName()));
                fieldList.add(field);
                values.append("?");
            }
            String insertQuery = String.format(
                    "INSERT INTO %s (" + columns + ") VALUES (" + values + ")",
                    convertToSnakeCase(className));
            preparedStatement = connection.prepareStatement(insertQuery);
            int parameterIndex = 1;
            for (Field field : fieldList) {
                if (field.getType().isEnum()) {
                    preparedStatement.setObject(parameterIndex++, field.get(toSave).toString());
                } else {
                    preparedStatement.setObject(parameterIndex++, field.get(toSave));
                }
            }
            preparedStatement.executeUpdate();
            returned = toSave;
        } catch (Exception exception) {
            System.err.println(
                    String.format("Error occurred while saving the %s :\n  %s\n  > %s",
                            className,
                            toSave,
                            exception.getMessage()
                    )
            );
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.err.println("Error while closing :\n  > "
                        + e.getMessage()
                );
            }
        }
        return returned;
    }

    @Override
    public boolean deleteById(int id) {
        DbConnect dbConnect = new DbConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Class<?> clazz = getModel().getClass();
        String className = clazz.getSimpleName();
        boolean isDeleted = false;

        try {
            connection = dbConnect.createConnection();
            String query = String.format(
                    "DELETE FROM %s WHERE id = %s",
                    convertToSnakeCase(className),
                    id
            );
            preparedStatement = connection.prepareStatement(query);
            isDeleted = preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            System.err.println(
                    String.format("Error occurred while deleting the %s with id %s :\n  > %s",
                    className,
                    id,
                    exception.getMessage())
            );
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.err.println("Error while closing :\n  > "
                        + e.getMessage()
                );
            }
        }
        return isDeleted;
    }

    @Override
    public T update(T toUpdate) {
        DbConnect dbConnect = new DbConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Class<?> clazz = toUpdate.getClass();
        String className = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder dataUpdate = new StringBuilder();
        List<Field> fieldList = new ArrayList<>();
        T returned = null;

        try {
            connection = dbConnect.createConnection();
            for (Field field : fields) {
                field.setAccessible(true);
                if (!dataUpdate.isEmpty()) {
                    dataUpdate.append(", ");
                }
                dataUpdate.append(
                        String.format(
                                "%s = ?",
                                convertToSnakeCase(field.getName())
                        )
                );
                fieldList.add(field);
            }
            String query = String.format(
                    "UPDATE %s SET %s WHERE id = %s",
                    convertToSnakeCase(className),
                    dataUpdate,
                    getModelId(toUpdate)
            );
            preparedStatement = connection.prepareStatement(query);
            int parameterIndex = 1;
            for (Field field : fieldList) {
                preparedStatement.setObject(parameterIndex++, field.get(toUpdate));
            }
            preparedStatement.executeUpdate();
            returned = toUpdate;
        } catch (Exception exception) {
            System.err.println(
                    String.format("Error occurred while updating the %s :\n  %s\n  > %s",
                            className,
                            toUpdate,
                            exception.getMessage()
                    )
            );
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.err.println("Error while closing :\n  > "
                        + e.getMessage()
                );
            }
        }
        return returned;
    }

    @Override
    public List<T> findAll() {
        DbConnect dbConnect = new DbConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Class<?> clazz = model.getClass();
        String className = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        List<T> dataList = new ArrayList<>();

        try {
            connection = dbConnect.createConnection();
            String query = "SELECT * FROM " + convertToSnakeCase(className);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.getType() == LocalDate.class) {
                        field.set(model,
                                resultSet.getDate(convertToSnakeCase(field.getName())).toLocalDate()
                        );
                    } else if (field.getType() == LocalDateTime.class) {
                        field.set(model,
                                resultSet.getTimestamp(convertToSnakeCase(field.getName())).toLocalDateTime()
                        );
                    } else if (field.getType().isEnum()) {
                        field.set(model,
                                EnumConverter.convertStringToEnum((Class) field.getType(),
                                        resultSet.getString(convertToSnakeCase(field.getName()))
                                )
                        );
                    } else {
                        field.set(model, resultSet.getObject(convertToSnakeCase(field.getName())));
                    }
                }
                dataList.add(model);
            }
        } catch (Exception exception) {
            System.err.println(
                    String.format("Error occurred while finding all %ss :\n  > %s",
                            className,
                            exception.getMessage()
                    )
            );
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.err.println("Error while closing :\n  > "
                        + e.getMessage()
                );
            }
        }
        return dataList;
    }

    @Override
    public T findById(int id) {
        DbConnect dbConnect = new DbConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Class<?> clazz = model.getClass();
        String className = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        T data = null;

        try {
            connection = dbConnect.createConnection();
            String query = "SELECT * FROM " + convertToSnakeCase(className);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.getType() == LocalDate.class) {
                        field.set(model,
                                resultSet.getDate(convertToSnakeCase(field.getName())).toLocalDate()
                        );
                    } else if (field.getType() == LocalDateTime.class) {
                        field.set(model,
                                resultSet.getTimestamp(convertToSnakeCase(field.getName())).toLocalDateTime()
                        );
                    } else if (field.getType().isEnum()) {
                        field.set(model,
                                EnumConverter.convertStringToEnum((Class) field.getType(),
                                        resultSet.getString(convertToSnakeCase(field.getName()))
                                )
                        );
                    } else {
                        field.set(model, resultSet.getObject(convertToSnakeCase(field.getName())));
                    }
                }
                data = model;
            }
        } catch (Exception exception) {
            System.err.println(
                    String.format("Error occurred while finding all %ss :\n  > %s",
                            className,
                            exception.getMessage()
                    )
            );
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.err.println("Error while closing :\n  > "
                        + e.getMessage()
                );
            }
        }
        return data;
    }

    private Integer getModelId(Object objectModel) throws Exception{
        Class<?> clazz = getModel().getClass();
        Field[] fields = clazz.getDeclaredFields();
        Integer id = null;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals("id")) {
                id = (int) field.get(objectModel);
            }
        }
        return id;
    }
}
