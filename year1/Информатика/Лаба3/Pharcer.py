from xml.etree import ElementTree
f=open('res.txt', 'w')
tree = ElementTree.parse('monday.xml')
root = tree.getroot()
f.write(root.tag+':'+ '\n')
for i in range (len(root)):
    f.write(' '+root[i].tag+':'+'\n')
    for j in range (len(root[i])):
        f.write(('  '+root[i][j].tag+':'+' '+'"'+root[i][j].text+'"'+'\n'))
f.close()