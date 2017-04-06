# Selenium Template
*	This is a base project for projects, incorporating Comcast Selenium Automation Framework (CSAF).
*	This selenium project uses TestNG framework for Automation testing. 

# Framework Features:
*	TesTNG and Maven based project that supports parallel execution.
*	It supports package execution.
*	Multibrowser/MultiENV supported
*	Logger statements are implemented to troubleshoot the issues with INFO, FAIL, WARN and DEBUG levels.
*	Customized HTML and Summary reports for the test case level and suite level
*	Page Object Model / Locators for each page classes
*	ALM integration with REST API
*	Grid execution supported
*	Dump file will be created for each test case and you can add/retrieve the data to/from the dump file and also it will keep track of the test method status. 
*	Interactive mode supported<br>
 For More Information on Framework Features - Refer to the link below
Read the Wiki page about the framework in the following link [Selenium template Wiki](https://github.comcast.com/quality-driven-delivery/Selenium_template/wiki) to understand the framework in details.

# How to clone the project?
To create a new git repostiory using this project as a code base, follow the steps below:
* Create a new empty repository in github with your PROJECT_NAME.
* Once done, from your local machine and start git bash/shell and run the following command:<br>
    ``` git clone --bare https://github.comcast.com/quality-driven-delivery/Selenium_template.git```
* This will create **Selenium_template.git** folder in the directory git shell was started from.
* Navigate inside the folder on shell using command:<br> 
    ```cd Selenium_template.git``` 
* Now push this bare repository into your newly created repository using the following command:<br>
     ```  git push --mirror "URL of your new repository" ```
* Once done, confirm the push on git repository in your browser
* Also, you can delete the **Selenium_template.git** folder, as it is of no significance<br> 
**Note**: This is a one time process. Once you have your own project, use the steps below to download your project
to local machines

# How to download the project?

*	To download the project in your Local computer open Git Bash/Git Shell and run the following command:<br>
     ```  git clone "URL of your new repository" ```
*	Once complete, Import as a maven project in Eclipse and do the Maven update.<br>
	**Note**: Make sure you have JDK 1.8 and Maven installed.

# VDIs:
TODO: After project is cloned

# WebTop BVT Job URL:
TODO: After project is cloned

# ALM Details:
TODO: After project is cloned

# Do's and Don'ts

* Make sure to push your tested code to remote github everyday. <br>
Team should follow the Do's and Don'ts listed [here](https://github.comcast.com/quality-driven-delivery/Selenium_template/wiki/Do's-and-Don'ts) 
	
# Template Test and Page Methods
[Template Test Methods](https://github.comcast.com/quality-driven-delivery/Selenium_template/blob/master/src/test/java/com/comcast/template/TemplateTest1.java)<br>
[Minimal Viable Test Method](https://github.comcast.com/quality-driven-delivery/Selenium_template/blob/master/src/test/java/com/comcast/template/MinimalViableTest.java)<br>
[Template Page Methods](https://github.comcast.com/quality-driven-delivery/Selenium_template/blob/master/src/main/java/com/comcast/pages/TemplatePage.java)<br>



# References
[Core Selenium Framework](https://github.comcast.com/quality-driven-delivery/BSA_Selenium_Core)<br>
[Selenium Framework Wiki](https://github.comcast.com/quality-driven-delivery/Selenium_template/wiki)<br>
[BSD COE Slack Channel](https://cim.slack.com/messages/bsd_qe_coe)<br>
[Selenium Template Project](https://github.comcast.com/quality-driven-delivery/Selenium_template)<br>
[Recommended Page Methods](https://github.comcast.com/quality-driven-delivery/Selenium_template/wiki/Recommended-Page-Methods)
