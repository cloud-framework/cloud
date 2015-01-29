package com.cloud.spider.entity.constants;

import java.util.TreeMap;

public class EnumType {
    public enum ProductType {
        jiandanBoringPic(1, "煎蛋无聊图");

        int _value = 0;
        String _message = "";

        public String getMessage() {
            return this._message;
        }

        private static TreeMap<Integer, ProductType> _map;

        static {
            _map = new TreeMap<Integer, ProductType>();
            for (ProductType num : ProductType.values()) {
                _map.put(new Integer(num.value()), num);
            }
        }

        public static ProductType lookup(int value) {
            return _map.get(new Integer(value));
        }

        ProductType(int value, String message) {
            this._value = value;
            this._message = message;
        }

        public int value() {
            return this._value;
        }
    }

}
