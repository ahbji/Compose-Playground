//
//  GeometryReaderTutorial.swift
//  playground
//
//  Created by Suraj-GP on 08/12/20.
//

import SwiftUI

struct GeometryReaderTutorial: View {
    var body: some View {
        GeometryReader { global in
            ScrollView(.horizontal, showsIndicators: false) {
                GeometryReader { local in
                    Text("\(global.frame(in: .global).minX)")
                        .font(.title)
                }
            }
            .frame(width: .infinity, height: /*@START_MENU_TOKEN@*/100/*@END_MENU_TOKEN@*/, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
        }
    }
}

struct GeometryReaderTutorial_Previews: PreviewProvider {
    static var previews: some View {
        GeometryReaderTutorial()
    }
}
