package com.dumptruckman.minecraft.pluginbase.config.field;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FieldInstance {

    private Object containingObject;
    private String[] name;
    private int nameIndex = 0;
    private FieldMap fieldMap;
    private Field field;

    FieldInstance(Object parentObject, @NotNull String... name) {
        this.containingObject = parentObject;
        this.name = name;
    }

    @Nullable
    FieldInstance locateField() {
        fieldMap = FieldMapper.getFieldMap(containingObject.getClass());
        field = fieldMap.getField(name[nameIndex]);
        if (field == null) {
            return null;
        }
        if (nameIndex + 1 < name.length) {
            if (field.hasChildFields()) {
                nameIndex++;
                containingObject = field.getValue(containingObject);
                if (containingObject == null) {
                    return null;
                }
                return locateField();
            } else {
                return null;
            }
        }
        return this;
    }

    public Object getFieldValue() {
        return field.getValue(containingObject);
    }

    public void setFieldValue(Object value) {
        field.setValue(containingObject, value);
    }
}