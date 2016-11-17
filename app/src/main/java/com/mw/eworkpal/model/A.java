package com.mw.eworkpal.model;

import java.io.Serializable;

/**
 * Created by gao_chun on 2016-11-16.
 */

public class A implements Serializable {

    private static final long serialVersionUID = -8192267747162219141L;

    public String email;
    public String address;
    public String name;
    public Author author;


    public class Author implements Serializable {
        private static final long serialVersionUID = -3763442542685948189L;

        public String email;
        public String address;
        public String name;

        @Override
        public String toString() {
            return "Author{" +
                    "email='" + email + '\'' +
                    ", address='" + address + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "A{" +
                "email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", author=" + author +
                '}';
    }
}
