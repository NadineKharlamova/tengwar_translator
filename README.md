# TengwarTranslator

Android-приложение — офлайн-переводчик между **русским** и **синдаринским** (язык Толкина) с отображением результата алфавитом **тенгвар**.

## Возможности

- **Двунаправленный перевод**: русский → синдарин и синдарин → русский
- **Пошаговый перевод через английский** — промежуточный результат на английском языке отображается в интерфейсе
- **Полностью офлайн** — встроенный словарь, не требует доступа в сеть
- **Распознавание фраз** — поиск наиболее длинного совпадения (жадный алгоритм)
- **Морфологические срабатывания** — обработка типичных окончаний при поиске слов
- **Отображение тенгваром** — шрифт Alcarin Tengwar (CSUR PUA), режим ómatehtar (гласные-тэхтары над согласными)

## Стек технологий

| Компонент | Версия |
|-----------|--------|
| Kotlin | 1.9.24 |
| Android Gradle Plugin | 8.5.2 |
| Jetpack Compose BOM | 2024.06.00 |
| Material 3 | — |
| compileSdk / targetSdk | 34 |
| minSdk | 24 |

## Структура проекта

```
app/src/main/java/com/tengwar/translator/
├── domain/          # Логика перевода: Direction, Lexicon, Translator
├── tengwar/         # Транслитерация в тенгвар: TengwarTransliterator, mappings
└── ui/              # Compose-интерфейс: MainActivity, TranslatorScreen,
                     # TranslatorViewModel, TengwarText, тема
```

## Сборка

```bash
# Debug-сборка
./gradlew assembleDebug

# Или через bat-скрипт (Windows)
build.bat
```

Готовый APK: `app/build/outputs/apk/debug/app-debug.apk`

## Шрифт

Используется шрифт **Alcarin Tengwar**, распространяемый по лицензии SIL Open Font License.
Текст лицензии: `app/src/main/assets/licenses/alcarin-tengwar-OFL.txt`
