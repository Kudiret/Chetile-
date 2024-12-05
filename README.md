# Chetile - Android Приложение для работы с хадисами

**Chetile** — это Android приложение, которое позволяет пользователю просматривать случайные хадисы, записывать данные в Excel файл и взаимодействовать с базой данных хадисов.

## Основные функции:
- **Случайные хадисы**: Каждый раз при запуске приложение или по нажатию кнопки отображается случайный хадис из базы данных.
- **Запись данных в Excel**: Пользователи могут вводить данные в текстовое поле, и приложение сохранит их в заранее подготовленный Excel файл.
- **Работа с базой данных**: Приложение использует SQLite для хранения хадисов и позволяет выбирать случайный хадис.
- **Интерфейс**: Приложение использует Jetpack Compose для создания современного пользовательского интерфейса.

## Функциональность:
1. **База данных хадисов**:
   - Встроенная SQLite база данных для хранения хадисов.
   - Добавление хадисов вручную.
   - Получение случайного хадиса при каждом запуске приложения.

2. **Excel-файл**:
   - Создание нового Excel файла при отсутствии.
   - Обновление значений в существующем Excel файле.
   - Открытие и просмотр Excel файла с данными.

3. **Интерфейс пользователя**:
   - Основной экран с отображением случайного хадиса.
   - Кнопки для выбора столбца и дня недели для записи данных.
   - Поле ввода для записи значений в Excel.
   - Кнопка для открытия Excel файла.

## Используемые технологии:
- **Jetpack Compose** для создания UI.
- **SQLite** для хранения хадисов.
- **Apache POI** для работы с Excel файлами.

## Требования:
- Минимальная версия SDK: Android 8.0 (API 26)
- Целевая версия SDK: Android 12 (API 34)

## Установка и использование:
1. Склонируйте репозиторий:
   ```bash
   git clone https://github.com/yourusername/chetile.git









# Chetile - Android App for Working with Hadiths

**Chetile** is an Android application that allows users to view random Hadiths, write data to an Excel file, and interact with a Hadith database.

## Key Features:
- **Random Hadiths**: Every time the app is launched or when a button is pressed, a random Hadith is displayed from the database.
- **Write Data to Excel**: Users can enter data into a text field, and the app will save it to a pre-defined Excel file.
- **Database Interaction**: The app uses SQLite to store Hadiths and allows fetching a random Hadith.
- **User Interface**: The app uses Jetpack Compose for a modern, responsive UI.

## Functionality:
1. **Hadith Database**:
   - Built-in SQLite database for storing Hadiths.
   - Ability to add Hadiths manually.
   - Fetching a random Hadith each time the app is opened.

2. **Excel File**:
   - Creating a new Excel file if it doesn't exist.
   - Updating values in an existing Excel file.
   - Opening and viewing the Excel file containing data.

3. **User Interface**:
   - Main screen displaying a random Hadith.
   - Buttons to select columns and days of the week for data entry.
   - Input field for entering values into the Excel file.
   - Button to open the Excel file.

## Technologies Used:
- **Jetpack Compose** for building the UI.
- **SQLite** for storing Hadiths.
- **Apache POI** for working with Excel files.

## Requirements:
- Minimum SDK Version: Android 8.0 (API 26)
- Target SDK Version: Android 12 (API 34)

## Installation and Usage:
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/chetile.git

