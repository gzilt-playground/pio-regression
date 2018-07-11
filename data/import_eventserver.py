"""
Import sample data for regression engine
"""

import predictionio
import argparse, csv

def import_events(client, file):
  attr0Map = {'publisher': 0, 'influencer': 1, 'celebrity': 2, 'community': 3}

  with open(file, 'r+', encoding='UTF-8') as benchmarkData:
    reader = csv.reader(benchmarkData, delimiter=',')
    idx = 0;
    total = 0;
    for item in reader:
      if idx > 0:
        #print("%s events are imported." % attr0Map[item[0]])
        if (float(item[2]) > 0) or (float(item[3]) > 0) or (float(item[4]) > 0) or (float(item[5]) > 0) :
            client.create_event(
              event="$set",
              entity_type="publisher",
              entity_id=str(idx), # use the count num as user ID
              properties= {
                  "attr0": int(attr0Map[item[0]]), #"attr_type"
                  "attr1": int(item[1]), #"attr_category"
                  "attr2": round(float(item[2]), 2), #"attr_fb_ave_eng"
                  "attr3": round(float(item[3]), 2), #"attr_ig_ave_eng"
                  "attr4": round(float(item[4]), 2), #"attr_yt_ave_eng"
                  "attr5": round(float(item[5]), 2), #"attr_site_ave_view"
                  "attr6": int(item[6]), #"attr_format"
                  "attr7": int(item[8]), #"attr_guaranteed_view"
                  "plan": float(item[7])
            })
            total += 1
            print("Processing %s." % idx)
      idx += 1


  benchmarkData.close()
  print("%s total events are imported." % total)

if __name__ == '__main__':
  parser = argparse.ArgumentParser(
    description="Import sample data for classification engine")
  parser.add_argument('--access_key', default='invald_access_key')
  parser.add_argument('--url', default="http://localhost:7070")
  parser.add_argument('--file', default="data.csv")

  args = parser.parse_args()
  print(args)

  client = predictionio.EventClient(
    access_key=args.access_key,
    url=args.url,
    threads=5,
    qsize=500)
  import_events(client, args.file)
