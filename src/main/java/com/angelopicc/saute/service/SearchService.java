package com.angelopicc.saute.service;

import java.util.List;

public interface SearchService<T> {
    
    List<T> search(String query);
}
