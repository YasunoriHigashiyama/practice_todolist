package jp.co.neosystem.kadai.api.mapper;

import jp.co.neosystem.kadai.api.entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper {
	@Select("select * from TASK_MANAGEMENT")
	List<TaskEntity> findAll();
}
