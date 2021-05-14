import sys
from botok import Text
# in_str = """ལེ གས། བཀྲ་ཤིས་མཐའི་ ༆ ཤི་བཀྲ་ཤིས་  tr 
# ... བདེ་་ལེ གས། བཀྲ་ཤིས་བདེ་ལེགས་༡༢༣ཀཀ། 
# ... མཐའི་རྒྱ་མཚོར་གནས་པའི་ཉས་ཆུ་འཐུང་།། །།མཁའ།"""
s_arg = sys.argv
in_str = ""
for i in range(1,len(s_arg)):
    in_str += s_arg[i]+" " 
# in_str = 'བཀྲ་ཤིས་མཐའི་'
in_str = in_str.strip()
t = Text(in_str)
k = t.tokenize_chunks_plaintext
k = k.encode('utf-8')
print(len(k))
for i in k:
    print(i)
