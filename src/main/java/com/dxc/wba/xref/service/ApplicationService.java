package com.dxc.wba.xref.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dxc.wba.xref.dbmodel.Applibmembers;
import com.dxc.wba.xref.dbmodel.ApplicationFileModel;
import com.dxc.wba.xref.repository.ApplibRepository;
import com.dxc.wba.xref.repository.ApplicationRepository;
import com.dxc.wba.xref.type.ApplicationJson;
import com.dxc.wba.xref.type.HeaderType;
import com.dxc.wba.xref.type.TextResponse;

@Service
public class ApplicationService {

	@Autowired
	ApplicationRepository applicationRepository;

	@Autowired
	ApplibRepository applibRepository;
	
	
	public Object getbyAppandlib(String applName, String mainframeDomain) {
		ApplicationJson applicationJson = new ApplicationJson();
		appheaders();
		Object val = null;
		List<Applibmembers> applibmembers = null;

		applibmembers = applibRepository.findbyapplName(applName, mainframeDomain);

		if (CollectionUtils.isEmpty(applibmembers)) {
			List<ApplicationFileModel> a = applicationRepository.getApplicationfilesWildcards(applName,
					mainframeDomain);
			applicationJson.setData(a);
			applicationJson.setConfig(appheaders());
			val = applicationJson;

		} else {

			List<String> list = new ArrayList<>();

			for (Applibmembers value : applibmembers) {

				list.add(value.getLineText());
			}

			val = list;

		}

		return val;
	}

	

	public Object getbyAppFiles(String applName) {

		List<Applibmembers> applibmembers = null;
		applibmembers = applibRepository.findbyapplName(applName);
		TextResponse response = new TextResponse();
		Object val = null;

		List<String> list = new ArrayList<>();

		for (int i = 0; i < Math.min(applibmembers.size(), 200); i++) {
			Applibmembers value = applibmembers.get(i);
			String lineText = value.getLineText();
			if (lineText.length() > 200) {
				lineText = lineText.substring(0, 200);
			}
			if (lineText.indexOf("~") < 200) {
				lineText = lineText.replace("~", "");
			}
			list.add(value.getLineText());
		}

		return list;
	}

	public List<ApplicationFileModel> fetchAllApplicationswithWildcard(String appl_Id, String string) {
		// TODO Auto-generated method stub
		return applicationRepository.getApplicationfilesWildcards(appl_Id, string);
	}

	public Object getbyAppFiles(String applName, String mainframeDomain) {

		List<Applibmembers> applibmembers = null;
		applibmembers = applibRepository.findbyapplName(applName, mainframeDomain);
		TextResponse response = new TextResponse();
		Object val = null;

		List<String> list = new ArrayList<>();

		for (int i = 0; i < Math.min(applibmembers.size(), 200); i++) {
			Applibmembers value = applibmembers.get(i);
			String lineText = value.getLineText();
			if (lineText.length() > 200) {
				lineText = lineText.substring(0, 200);
			}
			if (lineText.indexOf("~") < 200) {
				lineText = lineText.replace("~", "");
			}
			list.add(value.getLineText());
		}

		return list;

	}

	

	private List<HeaderType> appheaders() {
		List<HeaderType> headerTypeList = new ArrayList<>();

		HeaderType headerType = new HeaderType();
		headerType.setHeader("MEMBER");
		headerType.setKey("appl_Id");

		HeaderType headerType2 = new HeaderType();
		headerType2.setHeader("APPLICATION");
		headerType2.setKey("appl_name");

		HeaderType headerType3 = new HeaderType();
		headerType3.setHeader("DESCRIPTION");
		headerType3.setKey("appl_name_Desc_1");
		headerTypeList.add(headerType);
		headerTypeList.add(headerType2);
		headerTypeList.add(headerType3);
		return headerTypeList;

	}

	public List<String> readFile(String fileName) throws FileNotFoundException, IOException {

		List<String> resulList = new ArrayList<>();
		try {
			Map<String, Object> map = new HashMap<>();
			File folder = new File("C:/Users/pdeshmukh27/Downloads/applicationfiles");
			File[] listOfFiles = folder.listFiles();
			String sb = " ";
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println("File " + listOfFiles[i].getName());

					sb = listOfFiles[i].getName();

					if (sb.equalsIgnoreCase(fileName)) {

						String str = null;
						JSONObject json = null;
						try (BufferedReader br = new BufferedReader(
								new FileReader("C:/Users/pdeshmukh27/Downloads/applicationfiles" + "/" + sb))) {
							while (br.ready()) {
								str = br.readLine(); // map.put("data",str);
								resulList.add(str);

							}
						}

						System.out.println(resulList);

					}
				}
			}
		} catch (Exception e) { // TODO: handle exception e.printStackTrace();
		}
		return resulList;

	}

	public List<String> readFileByWildcards(String fileName) throws FileNotFoundException, IOException {
		List<String> resulList = new ArrayList<>();
		try {
			Map<String, Object> map = new HashMap<>();
			if (fileName.endsWith("*") || (fileName.endsWith("+"))) {

				fileName = fileName.replace('*', ' ');
				fileName = fileName.replace('+', ' ');
			}
			File folder = new File("C:/Users/pdeshmukh27/Downloads/applicationfiles");
			File[] listOfFiles = folder.listFiles();
			String sb = " ";
			for (File file : listOfFiles) {
				if (file.getName().startsWith(fileName.trim())) {
					System.out.println("File " + file.getName());

					resulList.add(file.getName());

					/*
					 * if (sb.equalsIgnoreCase(fileName)) { String str = null; JSONObject json =
					 * null; try (BufferedReader br = new BufferedReader( new
					 * FileReader("C:/Users/kratnagiri/Downloads/JOBSPDS/JOBSPDS" + "/" + sb))) {
					 * while (br.ready()) { str = br.readLine(); // map.put("data",str);
					 * resulList.add(str);
					 *
					 * } }
					 *
					 * System.out.println(resulList);
					 *
					 * }
					 */
				}
			}
		} catch (Exception e) { // TODO: handle exception e.printStackTrace();
		}
		return resulList;

	}

	public List<String> readFileBasedOnApp(String appName) {

		List<String> list = new ArrayList<>();
		try {
			String fileName = applicationRepository.getApplicationFilesByapplName(appName).getAppl_Id();
			list = readFile(fileName);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return list;
	}
	public List<ApplicationFileModel> fetchAllApplicationswithWildcard(String Appl_Id) {
		// TODO Auto-generated method stub
		return applicationRepository.getApplicationfilesWildcards(Appl_Id);
	}

}
