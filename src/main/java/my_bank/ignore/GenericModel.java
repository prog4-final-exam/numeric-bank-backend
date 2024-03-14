package my_bank.ignore;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public class GenericModel<T> {
    private T model;
    private String className = model.getClass().getSimpleName();
}
