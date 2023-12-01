json_file = [i.strip().rstrip(',').replace('"', '') for i in open('/Users/andrey/Documents/Итмо лабы/Инфа/lab4/schedule.json', 'r', encoding='utf-8').readlines()]
json_length = len(json_file)
json_ind = 0


def json_dict():
    global json_ind

    res = {}

    while json_ind < json_length:
        json_ind += 1

        if json_file[json_ind] == '}':
            return res

        key, value = json_file[json_ind].split(': ')

        if value == '[':
            res[key] = json_list()
        elif value == '{':
            res[key] = json_dict()
        else:
            res[key] = value


def json_list():
    global json_ind

    res = []

    while json_ind < json_length:
        json_ind += 1

        if json_file[json_ind] == '[':
            res.append(json_list())
        elif json_file[json_ind] == ']':
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
