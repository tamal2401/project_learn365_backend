package org.learn365.user.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TemplateManagement
{
    private static Logger log = LoggerFactory.getLogger(
            TemplateManagement.class);

    public  String ReadFile(String path, String FileName)
            throws IOException
    {

        InputStream stream = null;
        BufferedReader br = null;
        InputStreamReader read = null;
        StringBuilder sbr = null;
        File f = null;
        try
        {
            if (FileName != null)
            {
                if (path != null)
                {
                    f = new File(path + FileName);
                    if (f.exists())
                    {
                        stream = new FileInputStream(f);
                    }
                } else
                {
                    stream = this.getClass().getClassLoader()
                            .getResourceAsStream(FileName);
                    // f = new File(getClass().getResource("/" +
                    // FileName).getFile());
                    log.info("html file name is ::" + f);
                }
                if (stream != null)
                {
                    log.info("Reading the html file ::" + stream);
                    // stream = new FileInputStream(f);
                    read = new InputStreamReader(stream);
                    br = new BufferedReader(read);
                    sbr = new StringBuilder();
                    String line;
                    if (br != null)
                    {
                        while ((line = br.readLine()) != null)
                        {
                            sbr.append(line);
                        }

                    }
                }
            } else
            {
                log.info(
                        "Html file is not on the location please check the html template");
            }
        } finally
        {

            if (stream != null)
            {
                stream.close();
            }
            if (read != null)
            {
                read.close();
            }
            if (br != null)
            {
                br.close();
            }

        }
        return sbr == null ? null : sbr.toString();
    }

}
