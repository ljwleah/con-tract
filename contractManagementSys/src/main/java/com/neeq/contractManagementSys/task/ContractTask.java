package com.neeq.contractManagementSys.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.neeq.contractManagementSys.domain.Check;
import com.neeq.contractManagementSys.domain.Contract;
import com.neeq.contractManagementSys.domain.Renewal;
import com.neeq.contractManagementSys.domain.TimeOfPayment;
import com.neeq.contractManagementSys.service.CheckService;
import com.neeq.contractManagementSys.service.ContractService;
import com.neeq.contractManagementSys.service.RenewalService;
import com.neeq.contractManagementSys.service.TimeOfPaymentService;

@Component
public class ContractTask {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private ContractService contractService;
	@Autowired
	private RenewalService renewalService;
	@Autowired
	private TimeOfPaymentService timeOfPaymentService;
	@Autowired
	private CheckService checkService;

	//@Scheduled(cron = "0/5 * * * * ?")
	@Scheduled(cron = "0 0 10 ? * *")//每天早上10：15触发 
	public void checkContractStatus() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date currentTime = new Date();
		String dateString = df.format(currentTime);
		List<Renewal> renewals = null;
		List<TimeOfPayment> timeOfPayments = null;
		List<Check> checks = null;
		try {
			Date today = df.parse(dateString);
			renewals = renewalService.findByAlertDate(today);
			renewals.forEach(renewal -> {
				Contract contract = contractService.findById(renewal.getContractId());
				String contractName = contract.getName();
				Integer group = contract.getGroupId();
				if(group == 1){
					this.mailSender.send(new MimeMessagePreparator() {
						public void prepare(MimeMessage mimeMessage) throws Exception {
							mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("zangge@neeq.com.cn"));
							//mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("lujw@neeq.com.cn"));
							mimeMessage.setFrom(new InternetAddress("rongby@neeq.com.cn"));
							mimeMessage.setSubject("续签提醒");
							mimeMessage.setText("合同名称：" + contractName + "，提醒内容：" +renewal.getContent());
						}
					});
				}
				else if(group == 2){
					this.mailSender.send(new MimeMessagePreparator() {
						public void prepare(MimeMessage mimeMessage) throws Exception {
							mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("yangjw@neeq.com.cn"));
							mimeMessage.setFrom(new InternetAddress("rongby@neeq.com.cn"));
							mimeMessage.setSubject("续签提醒");
							mimeMessage.setText("合同名称：" + contractName + "，提醒内容：" +renewal.getContent());
						}
					});
				}

			});
			timeOfPayments = timeOfPaymentService.findByAlertDate(today);
			timeOfPayments.forEach(timeOfPayment -> {
				Contract contract = contractService.findById(timeOfPayment.getContractId());
				String contractName = contract.getName();
				Integer group = contract.getGroupId();
				if(group == 1){
					this.mailSender.send(new MimeMessagePreparator() {
						public void prepare(MimeMessage mimeMessage) throws Exception {
							mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("zangge@neeq.com.cn"));
							mimeMessage.setFrom(new InternetAddress("contractalert@neeq.com.cn"));
							mimeMessage.setSubject("付款提醒");
							mimeMessage.setText("合同名称：" + contractName + "，提醒内容：" +timeOfPayment.getContent());
						}
					});
				}
				else if(group == 2L){
					this.mailSender.send(new MimeMessagePreparator() {
						public void prepare(MimeMessage mimeMessage) throws Exception {
							mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("yangjw@neeq.com.cn"));
							mimeMessage.setFrom(new InternetAddress("contractalert@neeq.com.cn"));
							mimeMessage.setSubject("付款提醒");
							mimeMessage.setText("合同名称：" + contractName + "，提醒内容：" +timeOfPayment.getContent());
						}
					});
				}
				
				
			});
			checks = checkService.findByAlertDate(today);
			checks.forEach(check -> {
				Contract contract = contractService.findById(check.getContractId());
				String contractName = contract.getName();
				Integer group = contract.getGroupId();
				if(group == 1){
					this.mailSender.send(new MimeMessagePreparator() {
						public void prepare(MimeMessage mimeMessage) throws Exception {
							mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("zangge@neeq.com.cn"));
							/*mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("lujw@neeq.com.cn"));*/
							mimeMessage.setFrom(new InternetAddress("contractalert@neeq.com.cn"));
							mimeMessage.setSubject("验收提醒");
							mimeMessage.setText("合同名称：" + contractName + "，提醒内容：" +check.getContent());
						}
					});
				}
				else if (group == 2){
					this.mailSender.send(new MimeMessagePreparator() {
						public void prepare(MimeMessage mimeMessage) throws Exception {
							mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("yangjw@neeq.com.cn"));
							mimeMessage.setFrom(new InternetAddress("contractalert@neeq.com.cn"));
							mimeMessage.setSubject("验收提醒");
							mimeMessage.setText("合同名称：" + contractName + "，提醒内容：" +check.getContent());
						}
					});
				}
				
			});
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
