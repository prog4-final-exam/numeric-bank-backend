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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
                if (field.getName().toLowerCase() == "id") {
                    continue;
                }
                if (field.get(toSave) == null) {
                    continue;
                }
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
                    convertToSnakeCase(className)
            );
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
                if (field.getName().toLowerCase() == "id") {
                    continue;
                }
                if (field.get(toUpdate) != null) {
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
    public List<T> findAll(String customRequest) {
        List<T> dataList = find(null, TABLE, null, customRequest);
        if (dataList.getFirst() == null) {
            dataList.clear();
        }
        return dataList;
    }

    @Override
    public List<T> findManyByKey(List<KeyAndValue> keyAndValueList, FindSourceType findSourceType, Object paramsObj, String customRequest) {
        return find(keyAndValueList, findSourceType, paramsObj, customRequest);
    }

    @Override
    public T findFirstOneByKey(List<KeyAndValue> keyAndValueList, FindSourceType findSourceType, Object paramsObj, String customRequest) {
        return find(keyAndValueList, findSourceType, paramsObj, customRequest).getFirst();
    }
    @Override
    public T findLastOneByKey(List<KeyAndValue> keyAndValueList, FindSourceType findSourceType, Object paramsObj, String customRequest) {
        return find(keyAndValueList, findSourceType, paramsObj, customRequest).getLast();
    }

    private List<T> find(List<KeyAndValue> keyAndValueList, FindSourceType findSourceType, Object paramsObj, String customRequest) {
        DbConnect dbConnect = new DbConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Class<?> clazz = model.getClass();
        String className = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        List<T> dataList = new ArrayList<>();
        dataList.add(null);
        Field[] paramsFields = null;

        try {
            connection = dbConnect.createConnection();
            String queryConstraint = "";
            String key;
            String value;
            Integer id;
            String classNameInSnakeCase = convertToSnakeCase(className);
            String source;
            String params = "";
            int paramsCount = 0;

            if (paramsObj != null) {
                Class<?> paramsClazz = paramsObj.getClass();
                paramsFields = paramsClazz.getDeclaredFields();
            }

            if (findSourceType == FUNCTION) {
                if (paramsObj != null) {
                    paramsCount = paramsFields.length;
                } else if (keyAndValueList != null) {
                    paramsCount = keyAndValueList.size();
                }
                params += ",? ".repeat(paramsCount).substring(1);
                source = "get_" + classNameInSnakeCase
                        + String.format("(%s)", params);
            } else if (findSourceType == TABLE && customRequest != null){
                source = String.format(
                        "(%s) AS %s",
                        customRequest,
                        classNameInSnakeCase
                );
            } else {
                source = classNameInSnakeCase;
            }

            if (keyAndValueList != null && findSourceType == TABLE) {
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

            String query = "SELECT * FROM " + source + queryConstraint;
            preparedStatement = connection.prepareStatement(query);

            if (findSourceType == FUNCTION) {
                if (paramsObj != null) {
                    int i = 1;
                    for (Field field : paramsFields) {
                        field.setAccessible(true);
                        preparedStatement.setObject(i, field.get(paramsObj));
                        i++;
                    }
                } else if (keyAndValueList != null) {
                    for (int j = 0; j < paramsCount; j++) {
                        key = convertToSnakeCase(keyAndValueList.get(j).getKey());
                        value = keyAndValueList.get(j).getValue();
                        if (key.contains("id")) {
                            preparedStatement.setObject(j + 1, Integer.valueOf(value));
                        } else {
                            preparedStatement.setObject(j + 1, value);
                        }
                    }
                }
            }

            resultSet = preparedStatement.executeQuery();

            T data;
            int i = 0;
            while (resultSet.next()) {
                if (i++ == 0) {
                    dataList.clear();
                }


                data = (T) getData(clazz);
                setFieldValue(resultSet, fields, data);
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

    private Object getData(Class clazz) throws IllegalAccessException {
        GenericModel genericModel = new GenericModel();
        Class<?> genericModelClass = genericModel.getClass();
        Field[] genericModelFields = genericModelClass.getDeclaredFields();

        for (Field genericModelField : genericModelFields) {
            genericModelField.setAccessible(true);
            if (genericModelField.getType() == clazz) {
                return genericModelField.get(genericModel);
            }
        }
        return null;
    }

    private void setFieldValue(ResultSet resultSet, Field[] fields, Object data) throws SQLException, IllegalAccessException {

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldNameInSnakeCase = convertToSnakeCase(field.getName());
            if (field.getType() == LocalDate.class) {
                field.set(data,
                        resultSet.getDate(fieldNameInSnakeCase).toLocalDate()
                );
            } else if (field.getType() == LocalDateTime.class) {
                field.set(data,
                        resultSet.getTimestamp(fieldNameInSnakeCase).toLocalDateTime()
                );
            } else if (field.getType().isEnum()) {
                if (resultSet.getString(fieldNameInSnakeCase) != null) {
                    field.set(data,
                            EnumConverter.convertStringToEnum((Class) field.getType(),
                                    resultSet.getString(fieldNameInSnakeCase)
                            )
                    );
                } else {
                    field.set(data, null);
                }
            } else if (
                    Arrays.stream(
                            GenericModel.class.getDeclaredFields()
                    ).toList().toString().contains(field.getType().getSimpleName())
            ) {
                Class<?> fieldClazz = field.getType();
                Field[] declaredFields = fieldClazz.getDeclaredFields();
                Object insideData = getData(fieldClazz);
                setFieldValue(resultSet, declaredFields, insideData);
                field.set(data, insideData);;
            } else {
                field.set(data, resultSet.getObject(fieldNameInSnakeCase));
            }
        }
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

    private Integer getModelId(Object objectModel) throws Exception {
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
