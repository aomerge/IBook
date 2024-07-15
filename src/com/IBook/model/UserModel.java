 package com.IBook.model;

 public class UserModel {

        private long id;
        private String name;
        private String lastName;
        private java.sql.Timestamp createDate;
        private java.sql.Timestamp updateDate;

        // Getters y setters
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public java.sql.Timestamp getCreateDate() {
            return createDate;
        }

        public void setCreateDate(java.sql.Timestamp createDate) {
            this.createDate = createDate;
        }

        public java.sql.Timestamp getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(java.sql.Timestamp updateDate) {
            this.updateDate = updateDate;
        }
}
