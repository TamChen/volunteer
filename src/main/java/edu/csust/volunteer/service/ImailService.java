package edu.csust.volunteer.service;

import edu.csust.volunteer.model.ApplicationEmail;


public interface ImailService {
    public void sendMailByAsynchronousMode(final ApplicationEmail email);
}