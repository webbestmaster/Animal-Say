const appUI = SpreadsheetApp.getUi();
const folderName = "translation-android";

function getFileContent(data) {
  // en-us -> enUs
  // const localisationName = localisationNameCell.replace(/\-\w/, value => value.at(1).toUpperCase());


  const dataString = Object.entries(data)
    .map((keyValue) => {
      const [key, value] = keyValue;

      return `    <string name="${key}">${value}</string>`;
    })
    .join('\n');

  return `<?xml version="1.0" encoding="utf-8"?>
<resources>
${dataString}
</resources>`
}

function makeLocalisationAndroid() {
  const localisationSpreadsheet = SpreadsheetApp.getActiveSpreadsheet();
  const spreadsheetFile = DriveApp.getFileById(localisationSpreadsheet.getId());
  const localisationSheet = localisationSpreadsheet.getActiveSheet();
  const localisationRange = localisationSheet.getDataRange().getValues();
  // remove first line witch contain locale ISO codes
  const localisationNameRow = localisationRange.shift();

  const folderToSave = DriveApp.getFoldersByName(folderName).next().createFolder(`localisation-${new Date().toISOString()}`);

  localisationNameRow
    .forEach((localisationNameCell, index) => {
      if (index === 0) {
        return null;
      }

      if (localisationNameCell.trim() === "") {
        return null;
      }

      const localisationData = localisationRange
        .reduce((acc, row) => {
          const key = row.at(0).trim();
          if (key === "") {
            return acc;
          }

          const value = row.at(index);

          acc[key] = value;

          return acc;
        }, {});

        const localeFolder = localisationNameCell === "default" ? folderToSave.createFolder("values") : folderToSave.createFolder(`values-${localisationNameCell}`);

        localeFolder.createFile(`strings.xml`, getFileContent(localisationData), MimeType.PLAIN_TEXT);

        Logger.log(`${localisationNameCell} is done.`);

    });

  spreadsheetFile.makeCopy(localisationSpreadsheet.getName(), folderToSave);

  appUI.alert("Done, check your google drive");
}
