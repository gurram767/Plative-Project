Set oShell = CreateObject ("Wscript.Shell") 
Dim strArgs
strArgs = "cmd /c D:\ProjectsNew\IntegratedFramework\Run.bat"
oShell.Run strArgs, 0, false