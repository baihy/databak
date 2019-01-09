/**
 * @项目名称: databak
 * @文件名称: MainInfo.java
 * @日期: 2019年1月9日
 * @版权: 2019 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发交付中心
 */
package com.zskj.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zskj.service.ConfigService;
import com.zskj.service.ExecuteSqlService;
import com.zskj.service.impl.ConfigServiceImpl;
import com.zskj.service.impl.ExecuteSqlServiceImpl;

/**
 * @author baihuayang
 *
 */
public class MainInfo {
	private Text usernameText;
	private Text passwordText;
	private Text sqlText;
	private DateTime subDateTime;

	private ConfigService configService;
	private ExecuteSqlService executeSqlService;
	private Shell shell;
	private Text ipText;

	/**
	 * 
	 */
	public MainInfo() {
		configService = new ConfigServiceImpl();
		executeSqlService = new ExecuteSqlServiceImpl();
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainInfo window = new MainInfo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		shell = new Shell();
		shell.setImage(new Image(display, MainInfo.class.getResourceAsStream("/images/32X32.ico")));
		shell.setSize(725, 410);
		shell.setText("中审数据备份工具");
		GridLayout gl_shell = new GridLayout(1, false);
		gl_shell.marginWidth = 0;
		gl_shell.verticalSpacing = 0;
		gl_shell.marginHeight = 0;
		gl_shell.horizontalSpacing = 0;
		shell.setLayout(gl_shell);

		Composite mainComposite = new Composite(shell, SWT.NONE);
		mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		mainComposite.setLayout(new GridLayout(1, false));

		Composite mComposite = new Composite(mainComposite, SWT.BORDER);
		mComposite.setLayout(new GridLayout(1, false));
		mComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite userComposite = new Composite(mComposite, SWT.NONE);
		userComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		userComposite.setLayout(new GridLayout(2, false));

		Label ipLabel = new Label(userComposite, SWT.NONE);
		ipLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		ipLabel.setText("ip地址");

		ipText = new Text(userComposite, SWT.BORDER);
		ipText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label usernameLabel = new Label(userComposite, SWT.RIGHT);
		GridData gd_usernameLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_usernameLabel.widthHint = 60;
		usernameLabel.setLayoutData(gd_usernameLabel);
		usernameLabel.setSize(48, 17);
		usernameLabel.setText("用户名");

		usernameText = new Text(userComposite, SWT.BORDER);
		usernameText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		usernameText.setSize(437, 23);

		Label passwordLabel = new Label(userComposite, SWT.RIGHT);
		GridData gd_passwordLabel = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_passwordLabel.widthHint = 60;
		passwordLabel.setLayoutData(gd_passwordLabel);
		passwordLabel.setSize(36, 17);
		passwordLabel.setText("密码");

		passwordText = new Text(userComposite, SWT.BORDER);
		passwordText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		passwordText.setSize(453, 23);

		Composite testComposite = new Composite(mComposite, SWT.NONE);
		testComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		testComposite.setLayout(new GridLayout(1, false));

		Button testButton = new Button(testComposite, SWT.NONE);
		testButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		testButton.setText("测试连接");

		Composite sqlComposite = new Composite(mComposite, SWT.NONE);
		sqlComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sqlComposite.setLayout(new GridLayout(2, false));

		Label sqlLabel = new Label(sqlComposite, SWT.RIGHT);
		GridData gd_sqlLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_sqlLabel.widthHint = 60;
		sqlLabel.setLayoutData(gd_sqlLabel);
		sqlLabel.setText("SQL");

		sqlText = new Text(sqlComposite, SWT.BORDER | SWT.WRAP);
		sqlText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite timeComposite = new Composite(mComposite, SWT.NONE);
		timeComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		timeComposite.setLayout(new GridLayout(2, false));

		Composite composite = new Composite(timeComposite, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, false, true, 1, 1);
		gd_composite.widthHint = 60;
		composite.setLayoutData(gd_composite);
		composite.setLayout(new GridLayout(1, false));

		Label Label1 = new Label(composite, SWT.NONE);
		Label1.setBounds(0, 0, 61, 17);
		Label1.setText("执行时间");

		Label Label2 = new Label(composite, SWT.NONE);
		Label2.setBounds(0, 0, 61, 17);
		Label2.setText("（每天）");
		subDateTime = new DateTime(timeComposite, SWT.BORDER | SWT.TIME);
		subDateTime.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		Composite optionComposite = new Composite(mComposite, SWT.NONE);
		optionComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		optionComposite.setLayout(new GridLayout(2, false));

		Button executeButton = new Button(optionComposite, SWT.NONE);
		executeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (validate()) {
					if (saveConfig()) {
						executeTask();
						MessageDialog.openInformation(shell, "提示：", "操作成功！");
					} else {
						MessageDialog.openError(shell, "提示：", "操作失败！");
					}
				}
			}
		});
		GridData gd_executeButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_executeButton.widthHint = 100;
		executeButton.setLayoutData(gd_executeButton);
		executeButton.setText("执行");

		Button saveButton = new Button(optionComposite, SWT.NONE);
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (validate()) {
					if (saveConfig()) {
						MessageDialog.openInformation(shell, "提示：", "保存成功！");
					} else {
						MessageDialog.openError(shell, "提示：", "保存失败！");
					}
				}
			}
		});
		GridData gd_saveButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_saveButton.widthHint = 100;
		saveButton.setLayoutData(gd_saveButton);
		saveButton.setText("保存配置");
		Composite footComposite = new Composite(mainComposite, SWT.BORDER);
		GridLayout gl_footComposite = new GridLayout(1, false);
		gl_footComposite.verticalSpacing = 0;
		gl_footComposite.marginWidth = 0;
		gl_footComposite.horizontalSpacing = 0;
		gl_footComposite.marginHeight = 0;
		footComposite.setLayout(gl_footComposite);
		footComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		Label footerLabel = new Label(footComposite, SWT.NONE);
		footerLabel.setAlignment(SWT.CENTER);
		footerLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		footerLabel.setText("版权所有 Copyright(C)2018  河南中审科技有限公司");
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private boolean saveConfig() {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("ip", ipText.getText());
		paramMap.put("username", usernameText.getText());
		paramMap.put("password", passwordText.getText());
		paramMap.put("sql", sqlText.getText());
		String cron = getCron(subDateTime);
		paramMap.put("cron", cron);
		return configService.save(paramMap);
	}

	private void executeTask() {
		executeSqlService.executeSql();
	}

	private String getCron(DateTime subDateTime) {
		StringBuilder cron = new StringBuilder();
		int hour = subDateTime.getHours();
		int minutes = subDateTime.getMinutes();
		int seconds = subDateTime.getSeconds();
		cron.append(seconds).append(" ").append(minutes).append(" ").append(hour).append(" * * ?");
		return cron.toString();
	}

	private boolean validate() {
		String ip = ipText.getText();
		if (ip == null || "".equals(ip)) {
			MessageDialog.openError(shell, "提示：", "ip地址不能为空");
			return false;
		}
		String username = usernameText.getText();
		if (username == null || "".equals(username)) {
			MessageDialog.openError(shell, "提示：", "用户名不能为空");
			return false;
		}
		String sql = sqlText.getText();
		if (sql == null || "".equals(sql)) {
			MessageDialog.openError(shell, "提示：", "Sql不能为空");
			return false;
		}
		return true;
	}
}
