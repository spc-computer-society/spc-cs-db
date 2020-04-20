#pragma once

#include <string>
#include <fstream>
#include <vector>

class CSVReader
{
private:

    std::vector<std::vector<std::string> > values;

public:

    CSVReader(const char* path, int row, int col, bool endLineComma = false)
    {
        values.clear();

        std::ifstream csvFile;
        csvFile.open(path);

        for (int y = 0; y < row; ++y)
        {
            char cword[256];
            std::vector<std::string> word;

            for (int x = 0; x < col; ++x)
            {
                char delimiter = ',';
		if (!endLineComma && x == col - 1) delimiter = '\n';

                csvFile.get(cword, 256, ',');
                csvFile.ignore();
                word.push_back(std::string(cword));
            }
            if (endLineComma) csvFile.ignore();
            values.push_back(word);
        }

        csvFile.close();
    }

    std::string GetValue(int row, int col)
    {
        return values.at(row).at(col);
    }

    // use with caution
    template <typename T>
    T GetValue(int row, int col)
    {
        std::string value = values.at(row).at(col);

        if (typeid(T) == typeid(int))           return std::stoi(value);
        else if (typeid(T) == typeid(float))    return std::stof(value);
        else if (typeid(T) == typeid(double))   return std::stod(value);
        else if (typeid(T) == typeid(char))     return value.at(0);
        else if (typeid(T) == typeid(bool))
        {
            if (value == "true")                return true;
            else if (value == "false")          return false;
            else                                return !(std::stoi(value) == false);
        }
        else return NULL;
    }
};
