# Lingoda Course Checker

It logs in to lingoda an searches for courses you specified in the `calender-whitelist.txt`.
All booked courses are written to the sorted `book-history.txt`

## Config

### `calender-whitelist.txt`
**Book a single course:**
`29.09.2018 08:00`
**Book courses in a range:**
`28.09.2018 09:00 - 11:00`

### `class_count.txt`
Current checked class number. Will be iterated. Init it with a real `commit` value out of a free course url in the calender.
E.g. `790823` for `/teacher/classes/commit/790823`.

### `config.properties`
```
pathDriver = C:/tools/selenium/
nameDriver = webdriver.chrome.driver
exeDriver = chromedriver.exe

chromeDriverExe = C:/tools/selenium/chromedriver.exe

pageURL = https://www.lingoda.com/de/login
teacherURL = https://teacher.lingoda.com

commitUrl = https://teacher.lingoda.com/teacher/classes/commit/

username = mail@your-email.de
password = 123

pusherUrl = https://xxx.pushnotifications.pusher.com/publish_api/v1/instances/xxx/publishes
authorization = xxx13xxx
```

## Build
`mvn clean package`

## Install
``` shell
@"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"
choco install selenium-chrome-driver
```

## Start
`java -jar target/lingoda-checker-1.0-shaded`
