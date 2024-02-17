import re

json_file = open('schedule.json', 'r', encoding='utf-8').readlines()
json_file = [re.sub(r',?\n$', '', i) for i in json_file]
json_file = [re.sub(r'^\s*', '', i) for i in json_file]

json_length = len(json_file)
json_ind = 0


def json_dict():
    global json_ind

    res = {}

    while json_ind < json_length:
        json_ind += 1
        if re.search(r'}$', json_file[json_ind]):
            return res

        line = re.search(r'"(.*)": (\{|\[|"(.*)")', json_file[json_ind])
        key, value = line.group(1), line.group(2)

        if value == '[':
            res[key] = json_list()
        elif value == '{':
            res[key] = json_dict()
        else:
            res[key] = line.group(3)


def json_list():
    global json_ind

    res = []

    while json_ind < json_length:
        json_ind += 1

        search = re.search(r'.$', json_file[json_ind]).group(0)

        if search == '[':
            res.append(json_list())
        elif search == ']':
            return res
        else:
            res.append(json_file[json_ind])


xml_file = open('schedule.xml', 'w', encoding='utf-8')
xml_tab = 1


def xml_dict(dictionary):
    global xml_tab

    for key, value in dictionary.items():
        if type(value) is str:
            xml_file.write('\t' * xml_tab + f'<{key}>{value}</{key}>' + '\n')
        elif type(value) is dict:
            xml_file.write('\t' * xml_tab + f'<{key}>' + '\n')
            xml_tab += 1

            xml_dict(value)

            xml_tab -= 1
            xml_file.write('\t' * xml_tab + f'</{key}>' + '\n')
        else:
            xml_list(key, value)


def xml_list(name, array):
    global xml_tab

    for item in array:
        xml_file.write('\t' * xml_tab + f'<{name}>{item}</{name}>' + '\n')


xml_file.write('<?xml version="1.0" encoding="UTF-8"?>' + '\n')
xml_file.write('<root>' + '\n')
xml_dict(json_dict())
xml_file.write('</root>')
