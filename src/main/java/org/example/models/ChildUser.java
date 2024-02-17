package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

    @Entity
    public class ChildUser extends User{

        @OneToOne(mappedBy = "userAccount")
        private Child child;

        public ChildUser() {}

        public ChildUser(String username, String password) {
            super(username, password);
        }

        public Child getChild() {
            return child;
        }

        public void setChild(Child child) {
            this.child = child;
        }
    }
