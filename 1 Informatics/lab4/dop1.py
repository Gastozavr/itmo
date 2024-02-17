from json2xml import json2xml
from json2xml.utils import readfromjson

json_data = readfromjson('schedule.json')

with open('schedule.xml', 'w', encoding='utf-8') as xml_file:
    xml_file.write(json2xml.Json2xml(json_data, item_wrap=False, attr_type=False).to_xml())
