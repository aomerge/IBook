package com.IBook.service.serviceDto;

import  com.IBook.model.LeadBookModel;
import java.util.List;

public interface  LeadBookDTO {
    public List<LeadBookModel> getLeadBooks();
    public LeadBookModel getLeadBookById(long id);

}