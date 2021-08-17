package jp.co.neosystem.kadai.api.controller;

import jp.co.neosystem.kadai.api.TaskApi;
import jp.co.neosystem.kadai.api.entity.TaskEntity;
import jp.co.neosystem.kadai.api.service.TaskService;
import jp.co.neosystem.kadai.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

		List<Task> result = new ArrayList<>();
		for (var entity : taskService.findAll()) {
			Task task = entityToBean(entity);
			result.add(task);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	private Task entityToBean(TaskEntity entity) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Task task = new Task();
		Long id = entity.getId();
		task.setId((int) id.longValue());
		task.setStart(format.format(entity.getStartDate()));
		task.setLimit(format.format(entity.getLimitDate()));
		task.setMemo(entity.getMemo());
		return task;
	}
}
