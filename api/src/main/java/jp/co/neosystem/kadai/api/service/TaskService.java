package jp.co.neosystem.kadai.api.service;

import jp.co.neosystem.kadai.api.entity.TaskEntity;
import jp.co.neosystem.kadai.api.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
	@Autowired
	private TaskMapper taskMapper;

	@Transactional
	public List<TaskEntity> select(boolean all, String search, boolean todo, String sort, String order, int limit, int offset) {
		return taskMapper.select(all, search, todo, sort, order, limit, offset);
	}

	@Transactional
	public long count(boolean all, String search, boolean todo) {
		return taskMapper.count(all, search, todo);
	}

	@Transactional
	public int insertTask(TaskEntity taskEntity) {
		taskEntity.setCreateDate(new Date());
		return taskMapper.insertTask(taskEntity);
	}

	@Transactional
	public int updateTask(TaskEntity taskEntity) {
		taskEntity.setUpdateDate(new Date());
		return taskMapper.updateTask(taskEntity);
	}

	@Transactional
	public int updateTaskStatus(long id, Date taskCompleteDate) {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setId(id);
		taskEntity.setTaskCompleteDate(taskCompleteDate);
		taskEntity.setUpdateDate(new Date());
		return taskMapper.updateTaskStatus(taskEntity);
	}

	@Transactional
	public int deleteTask(long id) {
		return taskMapper.deleteTask(id);
	}
}
