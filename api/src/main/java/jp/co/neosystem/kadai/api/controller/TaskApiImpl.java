package jp.co.neosystem.kadai.api.controller;

import jp.co.neosystem.kadai.api.TaskApi;
import jp.co.neosystem.kadai.api.entity.TaskEntity;
import jp.co.neosystem.kadai.api.service.TaskService;
import jp.co.neosystem.kadai.model.Task;
import jp.co.neosystem.kadai.model.TaskData;
import jp.co.neosystem.kadai.model.TaskPaging;
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
	public ResponseEntity<Task> taskGet(Boolean todo, Boolean done, String sort,
											  String order, Integer number, String search, Integer page) {

		LOGGER.info("todo: " + todo);

		List<TaskData> data = new ArrayList<>();
		for (var entity : taskService.findAll()) {
			var d = entityToBean(entity);
			data.add(d);
		}

		TaskPaging paging = new TaskPaging();
		paging.setTotal(1);

		Task result = new Task();
		result.setData(data);
		result.setPaging(paging);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	private TaskData entityToBean(TaskEntity entity) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		TaskData task = new TaskData();
		Long id = entity.getId();
		task.setId((int) id.longValue());
		task.setStart(format.format(entity.getStartDate()));
		task.setLimit(format.format(entity.getLimitDate()));
		task.setMemo(entity.getMemo());
		return task;
	}
}
