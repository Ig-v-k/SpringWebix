package com.project.webixs.logistic.controller.abstractLogger;

import com.project.webixs.logistic.common.CommonController;
import com.project.webixs.logistic.model.Logger;
import com.project.webixs.logistic.repository.LoggerRepository;
import com.project.webixs.logistic.session.UserBean;
import com.rits.cloning.Cloner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.GenericTypeResolver;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class AbstractLogger<T> extends CommonController {

  protected Cloner cloner;
  private Class<T> type;
  private HttpSession httpSession;
  @Autowired
  private LoggerRepository loggerRepository;
  @Value("${loggerConfig.createMessage}")
  private String createMessage;
  @Value("${loggerConfig.updateMessage}")
  private String updateMessage;
  @Value("${loggerConfig.deleteMessage}")
  private String deleteMessage;

  public AbstractLogger() {
	cloner = new Cloner();
	this.type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractLogger.class);
	this.httpSession = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession(true);
  }

  public void logCreateAction(T object) {
	loggerRepository.saveAndFlush(new Logger(((UserBean) httpSession.getAttribute("userBean")).getUser().getId(), Logger.ActionType.CREATE.toString(), createMessage.replace("{entity}", object.toString()), type.getSimpleName(), (byte) 1, ((UserBean) httpSession.getAttribute("userBean")).getUser().getCompanyId()));
  }

  public void logUpdateAction(T newObject, T oldObject) {
	loggerRepository.saveAndFlush(new Logger(((UserBean) httpSession.getAttribute("userBean")).getUser().getId(), Logger.ActionType.UPDATE.toString(), updateMessage.replace("{oldEntity}", oldObject.toString()).replace("{newEntity}", newObject.toString()), type.getSimpleName(), (byte) 1, ((UserBean) httpSession.getAttribute("userBean")).getUser().getCompanyId()));
  }

  public void logDeleteAction(T object) {
	loggerRepository.saveAndFlush(new Logger(((UserBean) httpSession.getAttribute("userBean")).getUser().getId(), Logger.ActionType.DELETE.toString(), deleteMessage.replace("{entity}", object.toString()), type.getSimpleName(), (byte) 1, ((UserBean) httpSession.getAttribute("userBean")).getUser().getCompanyId()));
  }

  public void logSpecificAction(String actionType, String actionDetails, String tableName) {
	loggerRepository.saveAndFlush(new Logger(((UserBean) httpSession.getAttribute("userBean")).getUser().getId(), actionType, actionDetails, tableName, (byte) 0, ((UserBean) httpSession.getAttribute("userBean")).getUser().getCompanyId()));
  }

}