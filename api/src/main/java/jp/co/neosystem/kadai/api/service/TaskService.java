package jp.co.neosystem.kadai.api.service;

import jp.co.neosystem.kadai.api.entity.TaskEntity;
import jp.co.neosystem.kadai.api.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
	@Autowired
	private TaskMapper taskMapper;

	@Transactional
	public List<TaskEntity> findAll() {
		return taskMapper.findAll();
	}
}
