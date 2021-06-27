package com.ingesoft2.mail;

import javax.mail.SendFailedException;

public interface EmailPort {
  public boolean sendEmail(EmailBody emailBody) throws SendFailedException;
}
