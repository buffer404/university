def test_case(tree, testline):
    for node in tree:
        if testline[node['attr']] == node['value']:
            if node['class']:
                return node['class']
            else:
                return test_case(node['childs'], testline)


def test_all(tree, test_table):
    tp = 0
    tn = 0
    fp = 0
    fn = 0
    for line in test_table:
        if test_case(tree, line) == line[0]:
            if line[0] == '0':
                tp += 1
            else:
                tn += 1
        else:
            if line[0] == '0':
                fn += 1
            else:
                fp += 1



    print('tp=%s  fp=%s\ntn=%s  fn=%s\n' % (tp, fp, tn, fn))
    print('acuraccy = %s' % ((tp + tn)/(tp + tn + fp + fn)))
    print('precision <p> = %s' % (tp /(tp + fp)))
    print('precision <e> = %s' % (tn /(tn + fn)))
    print('recall <p> = %s' % (tp /(tp + fn)))
    print('recall <e> = %s' % (tn /(tn + fp)))