
@echo off
taskkill /f /im "IEDriverServer.exe"
taskkill /f /im "IEDriverServer_32.exe"
taskkill /f /im "IENotWrking.exe"
taskkill /f /im "chromedriver.exe"
taskkill /f /im "QALogin.exe"
taskkill /f /im "iexplore.exe"
taskkill /f /im "WerFault.exe"

del /s /f /q c:\windows\temp\*.*
rd /s /q c:\windows\temp
md c:\windows\temp
del /s /f /q C:\WINDOWS\Prefetch
del /s /f /q %temp%\*.*
rd /s /q %temp%
md %temp%
del c:\WIN386.SWP

cls 