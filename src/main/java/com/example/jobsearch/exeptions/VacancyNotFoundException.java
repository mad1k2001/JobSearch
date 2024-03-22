package com.example.jobsearch.exeptions;

public class VacancyNotFoundException extends Exception{
    public VacancyNotFoundException() {
    }

    public VacancyNotFoundException(String message) {
        super(message);
    }
}
