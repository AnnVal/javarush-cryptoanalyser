# javarush-cryptoanalyser
������� ������ ��� ����������.������������ ����� � �������������� ����� ������.
������������� ����������� ������� �����,���� �� ����������.

###������ �������
� ��������� ������ � ������� ������� cd ������������� � ����������, � ������� ��������� ����������� ����.
����� ��������� ��������� �������:
```
chcp 1251
java -jar ./javarush-cryptoanalyzer.jar
```
������� ��������� ��������� ��� ����������� ����������� ����������� ����� ��� ������� ��������� ���� (��������� ������� ������� ������������).

###�����������
������������� ���������� ������,���������� �� ������� ����� � �������������� ������ ����������.
������ ������ ������ ������� �� ������ ��� ���������� �����������.
������,����������,�����������, ����� � ����� ����� �� ���� ������ ������.
��� ������� ����� ����������� ����������� ����, ��� ��������� ������ ����� ��������, �� ������������� ������ ����������, �� ��� ������ ���� **�������������** ���������.

###�������� �������
� �������� ������ ������� `com.javarush.cryptoanalyzer` ���������� ����� `Main`, � ������� ��������� ����� ����� � ����������.
� ������ `crypto` ���������� ������:
-`CharCoder` - �������� �� ����������/������������ ��������� �������� � ������������� ���������� �����
-`StringCoder`- �������� �� ����������/������������ ��������� ����� � ������������� ���������� �����
-`FileCoder` - �������� �� ����������/������������ ������ � ������������� ���������� �����, ���� ��� ���� ����������������
-`BruteDecoder` - �������� �� ������ �����, ��������� ��. "�������� ������"
� ������ ������`dialog` ��������� ����� `Dialog`, ���������� �� �������������� � �������������.

###���������� ������
��� ������ ������������ ������� ������ � ����������� ������������ ��� ������ ������������ ��������.
��� �������� ��������� ��������� � ������������ � ������ `BruteDecoder`  ���� ������� `fileMakesSense`, ������� ������������ �������������� ���������� ��������� �����, ��� ������� ������:
- ���������� � �������� ` , : ! ? `
- ������������� ��������� `, - � `
- � **��** ������������� ��������� `. " ! ? : �`, _����� ��������� ������,�.�.��������������,��� ��� ����� ���� ��������_

###�������� ������ ���������
����� ������� ���������� � ������� ��� ����� ���������� ������ ��� �����.
```
"C:\Program Files\Java\jdk1.8.0_151\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2.3\lib\idea_rt.jar=63313:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2.3\bin" -Dfile.encoding=windows-1251 -classpath "C:\Program Files\Java\jdk1.8.0_151\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\rt.jar;C:\Users\user\IdeaProjects\javarush-cryptoanalyser\out\production\javarush-cryptoanalyser" com.javarush.cryptoanalyzer.Main
Hello! This is a small programme for file encoding or decoding by Caesar's cipher
Pay attention:your file will be replaced by the resulting one!
Enter full file path for encodind or decoding
```
���� ���� �� ���������� ���� �� ����������, ��� �������� ������ ��� ��������.
���� ���� ����������, �� ��� ����� ���������� 3 �������� �� �����:
```
choose an operation: 1 - encode/2 - decode/3-crack (for 1 or 2 you should know the key)
```
���� �� �������� 1 ��� 2, �� ��� ��������� ������ ����. ��� ����� �������������� ����� ���� ����� �������� ��������.
```
Enter a positive integer - a key for cipher
```
�� ���� ������ ��������� �����������.
���� ������� ����� 3 (�����), �� �� ������ �������� ������ -������� ����������� � �������� ������ ������������.
```
�����"������"����"������*"�����?
Check the string above. Does it make sense? enter y/n
```
���� ����� �� ����� ������ ��� ����� `n` ����������� ������� ���������, ���� �� �������� ����������� ������:

```
����� ������ ���� ������* �����.
Check the string above. Does it make sense? enter y/n
y
File was successfully decoded`
```

##### ������ ��������� �����
>����� ������ ���� ������� �����.
>������ ���, ����� ����� �������, ��� �������� � ����������� ����� ��������.
>�� ����� � ����-�� ������ ������������� � ����������?
>�� �� ������ �� ���� ����� ��� �����, ������� ����� �� ������.
>�� ��� ������ �������� � �� ������...
>������ �� � ���, ������ �� ���� ����-�� ������?

##### ������ �����, ��������������� � ������������� ����� 9

>����.���������,�.������,*�������
>������� ������������������ �����*����������������������*��� !����������
>��������������*����������������������������������
>����������.����?��������������!��������꫻�����������!���
>����������������,!�����*�����!����
>�� �����,����������������,�.�����*�������!��

##### ������ �����, ���������������� �������� �����

>����� ������ ���� ������* �����.
>������ ���, ����� ����� �������, ��* �������� � ����������* ����� ��������.
>�� ����� � ����*�� ������ ������������� � ����������?
>�� �� ������ �� ���� ����� ��� �����, ������� ����� �� ������.
>�� ��� ������ �������� � �* ������...
>������ �� � ���, ������ �� ���� ����*�� ������?

�������, ������� ��� � ���������� �������� (�,�,- � ������ ����� ���������), �������� '*'.