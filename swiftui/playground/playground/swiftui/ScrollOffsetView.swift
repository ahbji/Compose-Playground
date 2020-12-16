//
//  ScrollOffsetView.swift
//  playground
//
//  Created by Suraj-GP on 16/12/20.
//

import SwiftUI

private struct ScrollOffsetPreferenceKey: PreferenceKey {
    static var defaultValue: CGPoint = .zero

    static func reduce(value: inout CGPoint, nextValue: () -> CGPoint) {
        value = nextValue()
    }
}

struct ScrollView<Content: View>: View {
    let offsetChanged: (CGPoint) -> Void
    let content: Content

    init(
        offsetChanged: @escaping (CGPoint) -> Void = { _ in },
        @ViewBuilder content: () -> Content
    ) {
        self.offsetChanged = offsetChanged
        self.content = content()
    }
    
    var body: some View {
        SwiftUI.ScrollView(.horizontal, showsIndicators: false) {
                HStack {
                    GeometryReader { geometry in
                        Color.clear.preference(
                            key: ScrollOffsetPreferenceKey.self,
                            value: geometry.frame(in: .named("scrollView")).origin
                        )
                    }.frame(width: 0, height: 0)
                    content
                }
            }
            .coordinateSpace(name: "scrollView")
            .onPreferenceChange(ScrollOffsetPreferenceKey.self, perform: offsetChanged)
        }
}

struct XontentView: View {
    @State private var offset: CGPoint = .zero
    var body: some View {
        ZStack {
        ScrollView(
            offsetChanged: { offset = $0 }
        ) {
            ForEach(Items, id: \.self) { i in
                    Text("Lorem Ipsum is simply dummy text")
                        .frame(width: /*@START_MENU_TOKEN@*/100/*@END_MENU_TOKEN@*/, height: /*@START_MENU_TOKEN@*/100/*@END_MENU_TOKEN@*/, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                }
        }
            Text("\(offset.x), \(offset.y)")
                .font(.title)
                .fontWeight(.heavy)
                .foregroundColor(Color.red)
                .multilineTextAlignment(.center)
        }
    }
}

struct XontentView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            XontentView()
                .previewDisplayName("ParallaxScroll")
        }
    }
}
