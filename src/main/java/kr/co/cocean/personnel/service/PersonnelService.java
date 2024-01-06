package kr.co.cocean.personnel.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import kr.co.cocean.main.dto.FileDTO;
import kr.co.cocean.personnel.dao.PersonnelDAO;
import kr.co.cocean.personnel.dto.HistoryDTO;
import kr.co.cocean.personnel.dto.PersonnelDTO;
import kr.co.cocean.personnel.dto.departmentDTO;

@Service
public class PersonnelService {
	private String root = "C:/upload/cocean/";
	@Autowired PersonnelDAO dao;
	Logger logger = LoggerFactory.getLogger(getClass());
	public int join(HashMap<String, Object> params, MultipartFile file, MultipartFile fileSignature) {
		String dpID = (String) params.get("departmentID");
		String rsID = (String) params.get("responsibility");
		logger.info("join 서비스접근 "+ params.get("employeeID"));
		logger.info("담당확인" + params.get("responsibility"));


		String employeeIDString = (String) params.get("employeeID");
		int employeeID = Integer.parseInt(employeeIDString);
		if(file.getSize()!=0) {
			UploadContext(file,employeeID);
		}
		if(fileSignature.getSize()!=0) {
			
			UploadSgniture(fileSignature,employeeID);
		}
		
		return dao.join(params);
	}
    private void UploadSgniture(MultipartFile fileSignature, int employeeID) {
    	FileDTO dto = new FileDTO();
    	String oriFileName = fileSignature.getOriginalFilename();
		String ext = oriFileName.substring(oriFileName.lastIndexOf("."));
		String newFileName = System.currentTimeMillis()+ext;	
		dto.setCategory("사원관리");
		dto.setIdx(employeeID);
		dto.setPath("signature");
		dto.setServerFileName(newFileName);
		dto.setOriFileName(oriFileName);
		dao.upload(dto);
		
		try {
			byte[] bytes = fileSignature.getBytes();
			Path path = Paths.get(root+"signature/"+newFileName);
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void UploadContext(MultipartFile file, int employeeID) {
    	FileDTO dto = new FileDTO();
    	String oriFileName = file.getOriginalFilename();
		String ext = oriFileName.substring(oriFileName.lastIndexOf("."));
		String newFileName = System.currentTimeMillis()+ext;	
		dto.setCategory("사원관리");
		dto.setIdx(employeeID);
		dto.setPath("profile");
		dto.setServerFileName(newFileName);
		dto.setOriFileName(oriFileName);
		dao.upload(dto);
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(root+"profile/"+newFileName);
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void updateEmployeeImg(int employeeID, MultipartFile fileSignature, MultipartFile file) {
		if(file.getSize()!=0) {
			updateContext(file,employeeID);
		}
		if(fileSignature.getSize()!=0) {
			
			updateSgniture(fileSignature,employeeID);
		}
		
	}
	private void updateSgniture(MultipartFile fileSignature, int employeeID) {
    	FileDTO dto = new FileDTO();
    	String oriFileName = fileSignature.getOriginalFilename();
		String ext = oriFileName.substring(oriFileName.lastIndexOf("."));
		String newFileName = System.currentTimeMillis()+ext;	
		dto.setCategory("사원관리");
		dto.setIdx(employeeID);
		dto.setPath("signature");
		dto.setServerFileName(newFileName);
		dto.setOriFileName(oriFileName);
		dao.update(dto);
		
		try {
			byte[] bytes = fileSignature.getBytes();
			Path path = Paths.get(root+"signature/"+newFileName);
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void updateContext(MultipartFile file, int employeeID) {
		FileDTO dto = new FileDTO();
    	String oriFileName = file.getOriginalFilename();
		String ext = oriFileName.substring(oriFileName.lastIndexOf("."));
		String newFileName = System.currentTimeMillis()+ext;	
		dto.setCategory("사원관리");
		dto.setIdx(employeeID);
		dto.setPath("profile");
		dto.setServerFileName(newFileName);
		dto.setOriFileName(oriFileName);
		dao.update(dto);
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(root+"profile/"+newFileName);
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<HashMap<String, Object>> getBranch() {
        return dao.getBranch();
    }

	public int updateEmployee(int employeeID, HashMap<String, Object> params) {
		
		String positionID =(String) params.get("positionID");
		String status =(String) params.get("status");
		String departmentID =(String) params.get("departmentID");
		String rankID =(String) params.get("rankID");
		String responID =(String) params.get("responID");

		return dao.updateEmployeeInfo(employeeID,positionID,status,departmentID,rankID,responID);
	}
	
	
	public List<HashMap<String, Object>> getBranchID(String branchID) {
		// TODO Auto-generated method stub
		return dao.getBranchID(branchID);
	}
	public List<HashMap<String, Object>> getHqID(String hqID) {
		return dao.getHqID(hqID);
	}
	public List<HashMap<String, Object>> getDepartmentText(String departmentText) {
		return dao.getDepartmentText(departmentText);
	}
	public List<departmentDTO> getChart() {
		return dao.getChart();
	}


	public List<HashMap<String, Object>> personnelList() {
		return dao.personnelList();
	}
	public List<HashMap<String, Object>> getSelectOptionBranch(String selectedBranchValue) {
		if(selectedBranchValue.equals("지점")) {
		}
		return dao.getSelectOptionBranch(selectedBranchValue);
	}

	public List<HashMap<String, Object>> searchPerson(String searchValue, String selectedOption) {
		// TODO Auto-generated method stub
		return dao.searchPerson(searchValue,selectedOption);
	}
	public Boolean checkDuplicateEmployeeID(String employeeID) {
		return dao.checkDuplicateEmployeeID(employeeID);
	}
	public HashMap<String, Object> detail(int parsedEmployeeID) {
		return dao.datail(parsedEmployeeID);
	}
	public List<HashMap<String, Object>> employeeHistory(int employeeID) {
		return dao.employeeHistory(employeeID);
	}
	public List<HashMap<String, Object>> workHistory(int employeeID) {
		return dao.workHistory(employeeID);
	}
	public List<HashMap<String, Object>> getPositionName() {
		return dao.getPositionName();
	}
	public List<HashMap<String, Object>> getRankName() {
		// TODO Auto-generated method stub
		return dao.getRankName();
	}
	public List<HashMap<String, Object>> departmentChangeLog(int employeeID) {
		return dao.departmentChangeLog(employeeID);
	}

	public int historySave(int employeeID, String startDate, String endDate, String organizationName, String remarks,
			String category) {
		return dao.historySave(employeeID,startDate,endDate,organizationName,remarks,category);
	}
	public int schistorySave(int employeeID, String startDate, String endDate, String organizationName, String remarks,
			String sccategory) {
		return dao.schistorySave(employeeID,startDate,endDate,organizationName,remarks,sccategory);
	}
	public void writeDepartmentChangeLog(int employeeID, String beforedpID, String afterdpID) {
		
		dao.writeDepartmentChangeLog(employeeID,beforedpID,afterdpID);
		
	}
	public void resetPassword(String password, String employeeID) {
		
		dao.resetPassword(password,employeeID);
	}
	public HashMap<String, Object> getdepartmentInfo(String departmentID) {
		return dao.getdepartmentInfo(departmentID);
	}
	public HashMap<String, Object> gethqInfo(String hqID) {
		return dao.gethqInfo(hqID);
	}
	public Integer updateRank(String rankID, String rankName, Boolean isActive) {
		return dao.updateRank(rankID,rankName,isActive);
	}
	public void updatePosition(String positionID, String positionName, Boolean isActive) {
		
		dao.updatePosition(positionID,positionName,isActive);
		
	}
	public void addPostion(String positionID, String positionName, Boolean isActive) {
			
		dao.addPostion(positionID,positionName,isActive);
	}
	public Boolean checkDuplicateAddpositionID(String addpositionID) {
		// TODO Auto-generated method stub
		return dao.checkDuplicateAddpositionID(addpositionID);
	}
	public void addRank(String rankID, String rankName, Boolean isActive) {
		
		dao.addRank(rankID,rankName,isActive);
		
	}
	public Boolean checkDuplicateAddRankID(String addRankID) {
		// TODO Auto-generated method stub
		return dao.checkDuplicateAddRankID(addRankID);
	}
	public List<HashMap<String, Object>> getRankNameActive() {
		return dao.getRankNameActive();
	}
	public List<HashMap<String, Object>> getPositionNameActive() {
		// TODO Auto-generated method stub
		return dao.getPositionNameActive();
	}
	public int addDepartment(String hqID, String departmentName, Boolean isActive) {
		// TODO Auto-generated method stub
		
		return dao.addDepartment(hqID,departmentName,isActive);
		
	}
	public List<HashMap<String, Object>> getBranchOrgID(String branchID) {
		return dao.getBranchOrgID(branchID);
	}
	public void addhq(String branchID, String hqName, Boolean isActive) {
		
		dao.addhq(branchID,hqName,isActive);
	}
	public void updateinfo(int employeeID, HashMap<String, Object> params) {
		
		String name =(String) params.get("name");
		String phoneNumber =(String) params.get("phoneNumber");
		String address =(String) params.get("address");
		dao.updateInfo(employeeID, address, name, phoneNumber);
	}
	public void editHq(String hqName, String hqID, Boolean isActive) {
		// TODO Auto-generated method stub
		dao.editHq(hqID,hqName,isActive);
		
	}
	public void editDp(String departmentName, String departmentID, Boolean isActive) {
		dao.editDp(departmentID,departmentName,isActive);
	}
	public int getDepartmentMembers(String departmentID) {
		return dao.getDepartmentMembers(departmentID);
	}
	public void addResponsibiliy(int departmentID, String responName) {

		
		dao.addResponsibiliy(departmentID,responName);
	}
	
	


	

}
