set "Ymd=%date:~,4%%date:~5,2%%date:~8,2%"

xcopy /s/e "E:\Jenkins\workspace\omc-ni-system\omc-simulator\bin" F:\OMC_NI_Release\%ymd%\omc-simulator\ /y

rem copy  "E:\Jenkins\workspace\omc-ni-system\omc-ni-framework\deploy\Omc-framework\PROJECT_ASSISTANT\SINGLE_EXE_IMAGE\DiskImages\DISK1\setup.exe" F:\OMC_NI_Release\%ymd%\omc-ni-framework.exe /y

copy  "E:\Jenkins\workspace\omc-ni-system\omc-ni-system\deploy\Omc-system\PROJECT_ASSISTANT\SINGLE_EXE_IMAGE\DiskImages\DISK1\setup.exe" F:\OMC_NI_Release\%ymd%\omc-ni-system.exe /y

exit 0