package com.yash.yits.mapper;

import com.yash.yits.domain.Project;
import com.yash.yits.form.ProjectForm;

public class ProjectMapper {

	public static ProjectForm domainForm(Project project){
		
		ProjectForm projectForm = new ProjectForm();
		projectForm.setId(project.getId());
		projectForm.setName(project.getName());
		return projectForm;
	}
}
