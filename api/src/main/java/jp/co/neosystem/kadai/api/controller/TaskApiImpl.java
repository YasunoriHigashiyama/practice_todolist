package jp.co.neosystem.kadai.api.controller;

import jp.co.neosystem.kadai.api.TaskApi;
import jp.co.neosystem.kadai.api.service.TaskService;
import jp.co.neosystem.kadai.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class TaskApiImpl implements TaskApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskApiImpl.class);

	@Autowired
	private TaskService taskService;

	@Override
	public ResponseEntity<List<Task>> taskGet(Boolean todo, Boolean done, String sort,
											  String order, Integer number, String search) {

		LOGGER.info("todo: " + todo);

		for (var entity : taskService.findAll()) {
			LOGGER.info(entity.getMemo());
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
}
