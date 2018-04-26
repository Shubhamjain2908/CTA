package in.hortari.cta.service.utils;


import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import in.hortari.cta.exception.MailException;


/**
 * Class for sending email 
 * @author SHUBHAM JAIN
 * @since 02-04-2018
 */
public class MailProcessor 
{
	private final String from;
	private final String password;
	private Properties props;
	private Session session;
	private MimeMessage message;
	private Multipart multipart;

	/**
	 * This is Default Constructor for testing purpose.
	 * 
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public MailProcessor() throws AddressException, MessagingException {
		
		from = "capabilityshowcase@gmail.com"; 
		password = "Pepcus12345";
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		multipart = new MimeMultipart();
	}

	/**
	 * This is Parameterize Constructor.
	 * 
	 * @param senderEmail
	 *            (Sender Email Address)
	 * @param senderPassword
	 *            (Sender Password)
	 * @param host
	 *            (Mail host example "smtp.gmail.com" for Gmail)
	 * @param port
	 *            (Port for that host example "587")
	 * @throws AddressException
	 * @throws MessagingException
	 */

	public MailProcessor(String senderEmail, String senderPassword, String host,
			String port) throws AddressException, MessagingException,
			MailException {

		if (senderEmail == null || senderEmail.length() <= 0) {
			throw MailException
					.createErrorMessage("Sender's Email id can't be Empty or Null.");
		} else if (!senderEmail
				.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
			throw MailException
					.createErrorMessage("Sender's Email id is not a Valid Mail ID.");
		}
		from = senderEmail;

		if (senderPassword == null || senderPassword.length() <= 0) {
			throw MailException
					.createErrorMessage("Password can't be Empty or Null.");
		}
		password = senderPassword;

		if (host == null || host.length() <= 0) {
			throw MailException
					.createErrorMessage("Host can't be Empty or Null.");
		}

		if (port == null || port.length() <= 0) {
			throw MailException
					.createErrorMessage("Port can't be Empty or Null.");
		}

		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		multipart = new MimeMultipart();
	}

	/**
	 * This function sets default name to attachment file.
	 * 
	 * @param to
	 * @param subject
	 * @param body
	 * @param fileAttachment
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws MailException
	 */
	public boolean sendMail(String to, String subject, String messageText,
			String htmlContent, String fileAttachment) throws AddressException,
			MessagingException, MailException {
		return sendMail(to, subject, messageText, htmlContent, fileAttachment,
				null);
	}

	/**
	 * This function sets user specific name to attachment file.
	 * 
	 * @param to
	 *            (Receiver Email Address)
	 * @param subject
	 *            (Email subject)
	 * @param messageText
	 *            (Email content, can be HTML content)
	 * @param htmlContent
	 * @param fileAttachment
	 *            (Absolute path of file to be Attached)
	 * @param fileName
	 *            (User specific Name of file)
	 * @return (true on Successful sending of mail otherwise false)
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public boolean sendMail(String to, String subject, String messageText,
			String htmlContent, String fileAttachment, String fileName)
			throws AddressException, MessagingException, MailException {

		// If Reciver's Email is invalid or not given then throw Exception.
		if (to == null || to.length() <= 0) {
			throw MailException
					.createErrorMessage("Reciver's Email id can't be Empty or Null.");
		} else if (!to
				.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
			throw MailException
					.createErrorMessage("Reciver's Email id is not a Valid Mail ID.");
		}
		// Add passed Mail id to Recipient's Address.
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		// If Subject of Email is not given then throw Exception.
		if (subject == null || subject.length() <= 0) {
			throw MailException
					.createErrorMessage("Subject can't be Empty or Null.");
		}
		// Set the Subject
		message.setSubject(subject);

		// IfMessage Text,Html Content & Attachment all three are not given then
		// throw Exception at least one should be given.
		if ((messageText == null || messageText.length() <= 0)
				&& (htmlContent == null || htmlContent.length() <= 0)
				&& (fileAttachment == null || fileAttachment.length() <= 0)) {
			throw MailException
					.createErrorMessage("Message Text,Html Content & Attachment all three can't be Empty or Null simultaneously.");
		}

		// create new multiple message parts
		MimeBodyPart messageBodyText = new MimeBodyPart();
		MimeBodyPart messageBodyHtml = new MimeBodyPart();
		MimeBodyPart messageAttachment = new MimeBodyPart();

		// Add the message text to first part
		messageBodyText.setText(messageText);
		// Add the message html to second part
		messageBodyHtml.setContent(htmlContent, "text/html");
		// Add Both parts to Multiple parts of final mail
		multipart.addBodyPart(messageBodyText);
		multipart.addBodyPart(messageBodyHtml);

		// Attach some file if given
		if (!(fileAttachment == null || fileAttachment.length() <= 0)) {
			File file = new File(fileAttachment);
			if (!file.exists()) {
				throw MailException
						.createErrorMessage("The file you attached does not exists or you have given wrong path.");
			} else {
				// Source will be given absolute path
				DataSource source = new FileDataSource(fileAttachment);
				messageAttachment.setDataHandler(new DataHandler(source));

				// set default fileName if not given
				if (fileName == null || fileName.length() <= 0) {
					String tempFileAttachment = fileAttachment;
					fileName = tempFileAttachment.substring((tempFileAttachment
							.replaceAll("\\\\", "/").lastIndexOf('/')) + 1,
							tempFileAttachment.length());
				} else {
					fileName = fileName + fileAttachment.substring(fileAttachment.lastIndexOf("."), fileAttachment.length());
				}

				// Custom file name that you want to give to your attachment
				// file
				messageAttachment.setFileName(fileName);
				// Add the message attachment to third part
				multipart.addBodyPart(messageAttachment);

			}
		}

		// Add this part to Multiple parts of your of final mail
		message.setContent(multipart);

		// Send the message
		Transport.send(message);

		return true;
	}
}
