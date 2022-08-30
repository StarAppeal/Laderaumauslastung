package de.starappeal.laderaumauslastung.api;

public enum FieldType {

    BOOLEAN {
        public Boolean parse(String value) {
            return Boolean.valueOf(value);
        }
    },

    CHAR {
        public Character parse(String value) {
            return value.charAt(0);
        }
    },

    DOUBLE {
        public Double parse(String value) {
            return Double.valueOf(value);
        }
    },

    INTEGER {
        public Integer parse(String value) {
            return Integer.valueOf(value);
        }
    },

    LONG {
        public Long parse(String value) {
            return Long.valueOf(value);
        }
    },

    STRING {
        public String parse(String value) {
            return value;
        }
    };

    public abstract Object parse(String value);

}