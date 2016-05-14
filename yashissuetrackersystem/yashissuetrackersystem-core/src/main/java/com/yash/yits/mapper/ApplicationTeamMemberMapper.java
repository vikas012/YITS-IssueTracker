package com.yash.yits.mapper;

import com.yash.yits.domain.ApplicationTeamMember;
import com.yash.yits.form.ApplicationTeamMemberForm;

public class ApplicationTeamMemberMapper {

	public static ApplicationTeamMemberForm domainForm(ApplicationTeamMember applicationTeamMember){
		
		ApplicationTeamMemberForm memberForm = new ApplicationTeamMemberForm();
		memberForm.setId(applicationTeamMember.getId());
		memberForm.setIsActive(applicationTeamMember.getIsActive());
		memberForm.setLastModifiedDateTime(applicationTeamMember.getLastModifiedDateTime());
		memberForm.setCreatedDateTime(applicationTeamMember.getCreatedDateTime());
		memberForm.setMember(MemberMapper.domainForm(applicationTeamMember.getMember()));
		return memberForm;
	}
}