package jp.co.neosystem.kadai.api.mapper;

import jp.co.neosystem.kadai.api.entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface TaskMapper {
	List<TaskEntity> select(boolean all, String search, boolean todo, String sort, String order, int limit, int offset);

	long count(boolean all, String search, boolean todo);

	int insertTask(TaskEntity taskEntity);

	int updateTask(TaskEntity taskEntity);

	int updateTaskStatus(TaskEntity taskEntity);

	int deleteTask(long id);
}
