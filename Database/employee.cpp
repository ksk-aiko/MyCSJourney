#include <iostream>
#include <vector>
#include <tuple>
using namespace std;

struct date {
    int day;
    int month;
    int year;
};

typedef tuple<int, string, string, date, string, date, string, string, string, string, bool, int, date> employeeType;

namespace employees {
    vector<employeeType> relation = {};
}

void printEmployeeRelation() {
    for (int i = 0; i < employees::relation.size(); i++) {
        cout << "--------printing employees-------" << endl;
        cout << "[";
        cout << "id: " << get<0>(employees::relation[i]);
        cout << "name: " << get<1>(employees::relation[i]);
        cout << "gender: " << get<2>(employees::relation[i]);
        cout << "birth_date: " << get<3>(employees::relation[i]).day << "/" << get<3>(employees::relation[i]).month << "/" << get<3>(employees::relation[i]).year;
        cout << "myNumber: " << get<4>(employees::relation[i]);
        cout << "hire_date: " << get<5>(employees::relation[i]).day << "/" << get<5>(employees::relation[i]).month << "/" << get<5>(employees::relation[i]).year;
        cout << "department: " << get<6>(employees::relation[i]);
        cout << "charge: " << get<7>(employees::relation[i]);
        cout << "position: " << get<8>(employees::relation[i]);
        cout << "branch: " << get<9>(employees::relation[i]);
        cout << "spouse: " << get<10>(employees::relation[i]);
        cout << "children: " << get<11>(employees::relation[i]);
        cout << "medicalCheckUp: " << get<12>(employees::relation[i]).day << "/" << get<12>(employees::relation[i]).month << "/" << get<12>(employees::relation[i]).year;
        cout << "------------------------" << endl;
    }
}

typedef tuple<int, string, string, string, string, string, string, string, string> patientType;
namespace patients {
    vector<patientType> relation = {};
}

void printPatientRelation() {
    for (int i = 0; i < patients::relation.size(); i++) {
        cout << "--------printing patients-------" << endl;
        cout << "[";
        cout << "id: " << get<0>(patients::relation[i]);
        cout << "name: " << get<1>(patients::relation[i]);
        cout << "family: " << get<2>(patients::relation[i]);
        cout << "species: " << get<3>(patients::relation[i]);
        cout << "biologicalSex: " << get<4>(patients::relation[i]);
        cout << "img: " << get<5>(patients::relation[i]);
        cout << "ownerName: " << get<6>(patients::relation[i]);
        cout << "birthDate: " << get<7>(patients::relation[i]);
        cout << "firstVisit: " << get<8>(patients::relation[i]);
        cout << "------------------------" << endl;
    }
}

int main() {
    employees::relation.push_back(tuple<int, string, string, date, string, date, string, string, string, string, bool, int, date>(1, "John Doe", "M", {1, 1, 1990}, "123456789", {1, 1, 2020}, "IT", "Manager", "Software Engineer", "New York", false, 0, {1, 1, 2020}));
    employees::relation.push_back(tuple<int, string, string, date, string, date, string, string, string, string, bool, int, date>(2, "Jane Doe", "F", {1, 1, 1990}, "123456789", {1, 1, 2020}, "IT", "Manager", "Software Engineer", "New York", false, 0, {1, 1, 2020}));
    printEmployeeRelation();

    patients::relation.push_back(tuple<int, string, string, string, string, string, string, string, string>(1, "Tom", "Cat", "Felis catus", "M", "img", "John Doe", "1/1/2020", "1/1/2020"));
    patients::relation.push_back(tuple<int, string, string, string, string, string, string, string, string>(2, "Jerry", "Mouse", "Mus musculus", "M", "img", "Jane Doe", "1/1/2020", "1/1/2020"));
    printPatientRelation();
}
