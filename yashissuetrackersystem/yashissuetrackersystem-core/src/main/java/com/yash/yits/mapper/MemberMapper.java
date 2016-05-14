package com.yash.yits.mapper;

import com.yash.yits.domain.Member;
import com.yash.yits.form.MemberForm;

public class MemberMapper {
	
	public static MemberForm domainForm(Member member){
		
		MemberForm memberForm = new MemberForm();
		memberForm.setContact(member.getContact());
//		memberForm.setCreatedBy(member.getCreatedBy());
		memberForm.setCreatedDateTime(member.getCreatedDateTime());
		memberForm.setEmail(member.getEmail());
		memberForm.setId(member.getId());
		memberForm.setIsActive(member.getIsActive());
//		memberForm.setLastModifiedBy(member.getLastModifiedBy());
		memberForm.setName(member.getName());
		memberForm.setMemberId(member.getManagerId());
		memberForm.setManagerName(member.getManagerName());
		return memberForm;
	}
}