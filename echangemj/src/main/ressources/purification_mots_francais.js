const fs = require('fs');
const path = require('path');

const sortStuff = function () {
  const data = fs.readFileSync(path.join(__dirname, '/mots_francais.txt'), 'latin1');

  const dataLines = data.split('\n');

  const seenWords = [];

  const newDataLines = dataLines.map(line => {
    if (line.includes('-') || line.includes(' ') || line.includes('!') || line.includes('.') || line.includes('\'') || line.includes(')')) {
      return null;
    }

    line = line.trim();

    line = line.toLowerCase();

    line = line.normalize('NFD').replace(/[\u0300-\u036f]/g, '');

    const regex = /^[a-z]+$/;

    if (!line.match(regex)) {
      console.log(line);
      return null;
    }

    if (seenWords.includes(line)) {
      return null;
    }

    seenWords.push(line);

    return line;
  }).filter(line => {
    if (line === null) {
      return false;
    }
    return true;
  });

  console.log(dataLines.length - seenWords.length);

  return newDataLines.join('\n');
};

fs.writeFileSync(path.join(__dirname, '/mots_francais_pur_utf-8.txt'), sortStuff(), 'utf-8');
