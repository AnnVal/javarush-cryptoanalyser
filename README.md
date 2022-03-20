# javarush-cryptoanalyser
Учебный проект для шифрования.дешифрования файла с использованием шифра Цезаря.
Предусмотрена возможность подбора ключа,если он неизвестен.

###Запуск проекта
В командной строке с помощью команды cd переместитесь в директорию, в которой находится исполняемый файл.
Далее выполните следующие команды:
```
chcp 1251
java -jar ./javarush-cryptoanalyzer.jar
```
Перевод кодировки необходим для корректного отображения содержимого файла при попытке подобрать ключ (программа требует участия пользователя).

###Ограничения
Предусмотрено шифрование файлов,написанных на русском языке с использованием правил пунктуации.
Каждая строка должна сосоять из одного или нескольких предложений.
Будьте,пожалуйста,внимательны, чтобы в конце файла не было пустой строки.
При подборе шифра учитывается возможность того, что последняя строка будет подписью, не завершающейся знаком пунктуации, но она должна быть **действительно** последней.

###Описание классов
В корневом пакете проекта `com.javarush.cryptoanalyzer` содержится класс `Main`, в котором находится точка входа в приложение.
В пакете `crypto` содержатся классы:
-`CharCoder` - отвечает за шифрование/дешифрование отдельных символов с использование известного ключа
-`StringCoder`- отвечает за шифрование/дешифрование отдельных строк с использование известного ключа
-`FileCoder` - отвечает за шифрование/дешифрование файлов с испоьзованием известного ключа, файл при этом перезаписывается
-`BruteDecoder` - отвечает за подбор ключа, подробнее см. "алгоритм взлома"
В пакете диалог`dialog` находится класс `Dialog`, отвечающий за взаимодействие с пользователем.

###Алрогоритм взлома
Для взлома применияется перебор ключей и привлечение пользователя для оценки получившихся значений.
Для снижения количства обращений к пользователю в классе `BruteDecoder`  есть функция `fileMakesSense`, которая осуществляет автоматическое отбрасывае вариантов ключа, при которых строка:
- начинается с символов ` , : ! ? `
- заканчивается символами `, - « `
- и **не** заканчивается символами `. " ! ? : »`, _кроме последней строки,т.к.предполагается,что она может быть подписью_

###Описание работы программы
После запуска приложения в консоли вам будет предложено ввести имя файла.
```
"C:\Program Files\Java\jdk1.8.0_151\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2.3\lib\idea_rt.jar=63313:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2.3\bin" -Dfile.encoding=windows-1251 -classpath "C:\Program Files\Java\jdk1.8.0_151\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_151\jre\lib\rt.jar;C:\Users\user\IdeaProjects\javarush-cryptoanalyser\out\production\javarush-cryptoanalyser" com.javarush.cryptoanalyzer.Main
Hello! This is a small programme for file encoding or decoding by Caesar's cipher
Pay attention:your file will be replaced by the resulting one!
Enter full file path for encodind or decoding
```
Если файл по указанному пути не существует, вас попросят ввести его повторно.
Если файл существует, то вам будет предложено 3 действия на выбор:
```
choose an operation: 1 - encode/2 - decode/3-crack (for 1 or 2 you should know the key)
```
Если вы выберете 1 или 2, то вам предложат ввести ключ. При вводе отрицательного числа ключ будет запрошен повторно.
```
Enter a positive integer - a key for cipher
```
На этом работа программы завершается.
Если выбрать опцию 3 (взлом), то на экране появится строка -вариант расшифровки с запросом мнения пользователя.
```
Кжзую"жрнизп"гэфю"жнлппэ*"фзмуф?
Check the string above. Does it make sense? enter y/n
```
Если текст не имеет смысла при вводе `n` продолжится перебор вариантов, пока не появится осмысленная строка:

```
Здесь должен быть длинны* текст.
Check the string above. Does it make sense? enter y/n
y
File was successfully decoded`
```

##### Пример входящего файла
>Здесь должен быть длинный текст.
>потому что, когда много строчек, мой алгоритм с пунктуацией лучше работает.
>но вдруг у кого-то другие представления о пунктуации?
>ну не писать же сюда текст про кошку, которая лежит на окошке.
>на нее светит солнышко и ей хорошо...
>почему бы и нет, должно же быть кому-то хорошо?

##### Пример файла, закодированного с использоанием ключа 9

>Сопъ.»очфрпц»л,ы.»офтцц,*»ыпуъыд
>шчычхь» ычг»учнок»хцчнч»ъыщч пуг»хч*»кфнчщтых»ъ»шьцуыькятп*»фь !п»щклчыкпыд
>цч»мощьн»ь»учнч*ыч»ощьнтп»шщпоъыкмфпцт«»ч»шьцуыькяттж
>ць»цп»штъкы.»рп»ъ?ок»ыпуъы»шщч»уч!уьг»учычщк«»фпрты»цк»чуч!упд
>цк»цпп»ъмпыты»ъчфц,!уч»т»п*»ючщч!чддд
>шч пхь»л,»т»цпыг»очфрцч»рп»л,ы.»учхь*ыч»ючщч!чж

##### Пример файла, восстановленного подбором ключа

>Здесь должен быть длинны* текст.
>потому что, когда много строчек, мо* алгоритм с пунктуацие* лучше работает.
>но вдруг у кого*то другие представления о пунктуации?
>ну не писать же сюда текст про кошку, которая лежит на окошке.
>на нее светит солнышко и е* хорошо...
>почему бы и нет, должно же быть кому*то хорошо?

Символы, которых нет в кодирующем алфавите (й,ё,- и другие знаки пунтуации), заменены '*'.