package jp.co.neosystem.kadai.api.controller;

import jp.co.neosystem.kadai.api.TaskApi;
import jp.co.neosystem.kadai.api.entity.TaskEntity;
import jp.co.neosystem.kadai.api.service.TaskService;
import jp.co.neosystem.kadai.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class TaskApiImpl implements TaskApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskApiImpl.class);

	@Autowired
	private TaskService taskService;

	/**
	 * タスクの取得
	 * */
	@Override
	public ResponseEntity<Task> taskGet(Boolean todo, Boolean done, String sort,
										String order, Integer number, String search, Integer page) {

		if (!todo && !done) {
			TaskPaging paging = new TaskPaging();
			paging.setTotal(0);
			Task result = new Task();
			result.setData(new ArrayList<>());
			result.setPaging(paging);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		boolean all = todo && done;

		int limit = (number == null || number == 0) ? 20 : number;
		int offset = (page == null) ? 0 : (page - 1) * limit;

		if (StringUtils.isEmpty(search)) {
			search = "%";
		} else {
			search = search
					.replace("$", "$$")
					.replace("%", "$%")
					.replace("_", "$_");
		}

		List<TaskData> data = new ArrayList<>();
		for (var entity : taskService.select(all, search, todo, sort, order, limit, offset)) {
			var d = entityToBean(entity);
			data.add(d);
		}

		long count = taskService.count(all, search, todo);
		TaskPaging paging = new TaskPaging();
		paging.setTotal((int) count);

		Task result = new Task();
		result.setData(data);
		result.setPaging(paging);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> taskIdDelete(String id) {
		taskService.deleteTask(Long.parseLong(id));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> taskIdPut(String id, ReqUpdateTask reqUpdateTask) {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");

		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setId(Long.parseLong(id));
		taskEntity.setTitle(reqUpdateTask.getTitle());
		taskEntity.setMemo(reqUpdateTask.getMemo());
		try {
			taskEntity.setStartDate(format.parse(reqUpdateTask.getStart()));
			taskEntity.setLimitDate(format.parse(reqUpdateTask.getLimit()));
		} catch (ParseException e) {
			LOGGER.warn(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		taskService.updateTask(taskEntity);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> taskIdStatusPatch(String id, ReqUpdateTaskStatus reqUpdateTask) {

		Date taskCompleteDate = null;
		if (reqUpdateTask.getStatus() != null && reqUpdateTask.getStatus()) {
			taskCompleteDate = new Date();
		}

		taskService.updateTaskStatus(Long.parseLong(id), taskCompleteDate);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResCreateTask> taskPost(ReqCreateTask reqCreateTask) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setTitle(reqCreateTask.getTitle());
		taskEntity.setMemo(reqCreateTask.getMemo());
		try {
			taskEntity.setStartDate(format.parse(reqCreateTask.getStart()));
			taskEntity.setLimitDate(format.parse(reqCreateTask.getLimit()));
		} catch (ParseException e) {
			LOGGER.warn(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		taskService.insertTask(taskEntity);
		LOGGER.info("id == {}", taskEntity.getId());

		ResCreateTask response = new ResCreateTask();
		response.setId(taskEntity.getId().intValue());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	private TaskData entityToBean(TaskEntity entity) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		TaskData task = new TaskData();
		Long id = entity.getId();
		task.setTitle(entity.getTitle());
		task.setId((int) id.longValue());
		task.setStart(format.format(entity.getStartDate()));
		task.setLimit(format.format(entity.getLimitDate()));
		task.setMemo(entity.getMemo());
		task.setStatus(entity.getTaskCompleteDate() != null);
		return task;
	}
}