package com.IBook.model;

public class LeadBookModel {
        private long id;
        private long bookId;
        private long userId;
        private java.sql.Date checkoutDate;
        private java.sql.Date dueDate;
        private java.sql.Date returnDate;
        private String conditionOnLoan;
        private String conditionOnReturn;
        private int renewals;
        private java.math.BigDecimal lateFee;

        // Getters y setters
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getBookId() {
            return bookId;
        }

        public void setBookId(long bookId) {
            this.bookId = bookId;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public java.sql.Date getCheckoutDate() {
            return checkoutDate;
        }

        public void setCheckoutDate(java.sql.Date checkoutDate) {
            this.checkoutDate = checkoutDate;
        }

        public java.sql.Date getDueDate() {
            return dueDate;
        }

        public void setDueDate(java.sql.Date dueDate) {
            this.dueDate = dueDate;
        }

        public java.sql.Date getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(java.sql.Date returnDate) {
            this.returnDate = returnDate;
        }

        public String getConditionOnLoan() {
            return conditionOnLoan;
        }

        public void setConditionOnLoan(String conditionOnLoan) {
            this.conditionOnLoan = conditionOnLoan;
        }

        public String getConditionOnReturn() {
            return conditionOnReturn;
        }

        public void setConditionOnReturn(String conditionOnReturn) {
            this.conditionOnReturn = conditionOnReturn;
        }

        public int getRenewals() {
            return renewals;
        }

        public void setRenewals(int renewals) {
            this.renewals = renewals;
        }

        public java.math.BigDecimal getLateFee() {
            return lateFee;
        }

        public void setLateFee(java.math.BigDecimal lateFee) {
            this.lateFee = lateFee;
        }
}