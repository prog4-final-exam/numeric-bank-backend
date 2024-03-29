package my_bank.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my_bank.dbConnection.DbConnect;
import my_bank.model.Enum.FindSourceType;
import my_bank.model.GenericModel;
import my_bank.model.KeyAndValue;

import static my_bank.model.Enum.FindSourceType.FUNCTION;
import static my_bank.model.Enum.FindSourceType.TABLE;
import static my_bank.repository.CaseConverter.convertToSnakeCase;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<T> dataList = find(null, null, null);
        if (dataList.getFirst() == null) {
            dataList.clear();
        }
        return dataList;
    }

    @Override
    public List<T> findManyByKey(List<KeyAndValue> keyAndValueList, FindSourceType findSourceType, Object params) {
        return find(keyAndValueList, findSourceType, params);
    }

    @Override
    public T findFirstOneByKey(List<KeyAndValue> keyAndValueList, FindSourceType findSourceType, Object paramsObj) {
        return find(keyAndValueList, findSourceType, null).getFirst();
    }
    @Override
    public T findLastOneByKey(List<KeyAndValue> keyAndValueList, FindSourceType findSourceType, Object paramsObj) {
        return find(keyAndValueList, findSourceType, paramsObj).getLast();
    }

    private List<T> find(List<KeyAndValue> keyAndValueList, FindSourceType findSourceType, Object paramsObj) {
        DbConnect dbConnect = new DbConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Class<?> clazz = model.getClass();
        String className = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        List<T> dataList = new ArrayList<>();
        dataList.add(null);

        Class<?> paramsClazz = paramsObj.getClass();
        Field[] paramsFields = paramsClazz.getDeclaredFields();

        try {
            connection = dbConnect.createConnection();
            String queryConstraint = "";
            String key;
            String value = "";
            Integer id;
            String sourceName = convertToSnakeCase(className);
            String params = "";
            //int statementCount = 0;

            if (findSourceType == FUNCTION && paramsObj != null) {
                //int paramsCount = ;
                //statementCount = paramsCount;
                params += ",? ".repeat(paramsFields.length).substring(1);
                sourceName = "get_" + sourceName
                        + String.format("(%s)", params);
            }

            if (keyAndValueList != null) {
                for (KeyAndValue keyAndValue : keyAndValueList) {
                    key = keyAndValue.getKey();
                    value = keyAndValue.getValue();

                    if (key != null) {
                        if (keyAndValueList.indexOf(keyAndValue) == 0) {
                            queryConstraint += " WHERE ";
                        } else {
                            queryConstraint += " AND ";
                        }

                        key = convertToSnakeCase(key);
                        if (key.contains("id")) {
                            id = Integer.valueOf(value);
                            queryConstraint += String.format(
                                    " %s = %s ",
                                    key, id
                            );
                        } else {
                            queryConstraint += String.format(
                                    " %s = '%s' ",
                                    key, value);
                        }
                    }
                }
            }

            String query = "SELECT * FROM " + sourceName + queryConstraint;
            preparedStatement = connection.prepareStatement(query);

            if (findSourceType == FUNCTION && paramsObj != null) {
                int i = 1;
                for (Field field : paramsFields) {
                    field.setAccessible(true);
                    preparedStatement.setObject(i, field.get(paramsObj));
                    i++;
                }
            }

            resultSet = preparedStatement.executeQuery();

            T data = null;
            int i = 0;
            while (resultSet.next()) {
                if (i++ == 0) {
                    dataList.clear();
                }
                GenericModel genericModel = new GenericModel();
                Class<?> genericModelClass = genericModel.getClass();
                Field[] modelGenericFields = genericModelClass.getDeclaredFields();

                for (Field modelGenericField : modelGenericFields) {
                    modelGenericField.setAccessible(true);
                    if (modelGenericField.getType() == clazz) {
                        data = (T) modelGenericField.get(genericModel);
                    }
                }

                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.getType() == LocalDate.class) {
                        field.set(data,
                                resultSet.getDate(convertToSnakeCase(field.getName())).toLocalDate()
                        );
                    } else if (field.getType() == LocalDateTime.class) {
                        field.set(data,
                                resultSet.getTimestamp(convertToSnakeCase(field.getName())).toLocalDateTime()
                        );
                    } else if (field.getType().isEnum()) {
                        field.set(data,
                                EnumConverter.convertStringToEnum((Class) field.getType(),
                                        resultSet.getString(convertToSnakeCase(field.getName()))
                                )
                        );
                    } else {
                        field.set(data, resultSet.getObject(convertToSnakeCase(field.getName())));
                    }
                }
                dataList.add(data);
            }
        } catch (Exception exception) {
            System.err.println(
                    String.format("Error occurred while finding %s.s :\n  > %s",
                            className,
                            exception.getMessage()
                    )
            );
        } finally {
            closeSession(connection, preparedStatement, resultSet);
        }
        return dataList;
    }

    private void closeSession(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
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
