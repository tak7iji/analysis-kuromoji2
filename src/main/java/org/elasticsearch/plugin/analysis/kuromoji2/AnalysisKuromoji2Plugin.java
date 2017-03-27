/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.plugin.analysis.kuromoji2;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.CharFilterFactory;
import org.elasticsearch.index.analysis.Kuromoji2JapaneseStopTokenFilterFactory;
import org.elasticsearch.index.analysis.Kuromoji2AnalyzerProvider;
import org.elasticsearch.index.analysis.Kuromoji2BaseFormFilterFactory;
import org.elasticsearch.index.analysis.Kuromoji2IterationMarkCharFilterFactory;
import org.elasticsearch.index.analysis.Kuromoji2KatakanaStemmerFactory;
import org.elasticsearch.index.analysis.Kuromoji2KeepPartOfSpeechFilterFactory;
import org.elasticsearch.index.analysis.Kuromoji2NumberFilterFactory;
import org.elasticsearch.index.analysis.Kuromoji2PartOfSpeechFilterFactory;
import org.elasticsearch.index.analysis.Kuromoji2ReadingFormFilterFactory;
import org.elasticsearch.index.analysis.Kuromoji2TokenizerFactory;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule.AnalysisProvider;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

public class AnalysisKuromoji2Plugin extends Plugin implements AnalysisPlugin {
    @Override
    public Map<String, AnalysisProvider<CharFilterFactory>> getCharFilters() {
        return singletonMap("kuromoji2_iteration_mark", Kuromoji2IterationMarkCharFilterFactory::new);
    }

    @Override
    public Map<String, AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
        Map<String, AnalysisProvider<TokenFilterFactory>> extra = new HashMap<>();
        extra.put("kuromoji2_baseform", Kuromoji2BaseFormFilterFactory::new);
        extra.put("kuromoji2_part_of_speech", Kuromoji2PartOfSpeechFilterFactory::new);
        extra.put("kuromoji2_keep_part_of_speech", Kuromoji2KeepPartOfSpeechFilterFactory::new);
        extra.put("kuromoji2_readingform", Kuromoji2ReadingFormFilterFactory::new);
        extra.put("kuromoji2_stemmer", Kuromoji2KatakanaStemmerFactory::new);
        extra.put("kuromoji2_ja_stop", Kuromoji2JapaneseStopTokenFilterFactory::new);
        extra.put("kuromoji2_number", Kuromoji2NumberFilterFactory::new);
        return extra;
    }

    @Override
    public Map<String, AnalysisProvider<TokenizerFactory>> getTokenizers() {
        return singletonMap("kuromoji2_tokenizer", Kuromoji2TokenizerFactory::new);
    }

    @Override
    public Map<String, AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        return singletonMap("kuromoji2", Kuromoji2AnalyzerProvider::new);
    }
}
